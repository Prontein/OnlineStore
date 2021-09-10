package ru.geekbrains.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.web.dtos.OrderItemDTO;
import ru.geekbrains.web.exceptions.ResourceNotFoundException;
import ru.geekbrains.web.model.Order;
import ru.geekbrains.web.model.OrderItem;
import ru.geekbrains.web.model.Product;
import ru.geekbrains.web.model.User;
import ru.geekbrains.web.repositories.OrderItemsRepository;
import ru.geekbrains.web.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartService cartService;
    private final ProductService productService;
    private final OrderItemsRepository orderItemsRepository;
    private final OrderRepository orderRepository;
    private final UserService userService;

    @Transactional
    public void saveOrderInBase (Principal principal) {
        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();

        orderItems = saveOrderItemInDB(orderItems, order);
        saveOrderInDB(order,orderItems, principal);
        cartService.clearCart();
    }

    private User clientInfo(Principal principal) {
        return userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
    }

    private List<OrderItem> saveOrderItemInDB(List<OrderItem> orders, Order order) {
        List<OrderItemDTO> items = cartService.getCartForCurrentUser().getItems();

        for (OrderItemDTO i : items) {
            OrderItem orderItem = new OrderItem();

            Product product = productService.findById(i.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Продукт с выбранным id:" + i.getProductId() + "не существут"));
            orderItem.setProduct(product);
            orderItem.setQuantity(i.getQuantity());
            orderItem.setPricePerProduct(i.getPricePerProduct());
            orderItem.setPrice(i.getPrice());
            orderItem.setOrder(order);

            orderItemsRepository.save(orderItem);
            orders.add(orderItem);
        }
        return orders;
    }

    private void saveOrderInDB (Order order, List<OrderItem> orderItems, Principal principal) {
        order.setOrderData(LocalDateTime.now());
        order.setTotalPrice(cartService.getCartForCurrentUser().getTotalPrice());
        order.setOrderItems(orderItems);
        order.setOrderName("Номер заказа: №" + order.getId());
        order.setUser(clientInfo(principal));

        orderRepository.save(order);
    }

}
