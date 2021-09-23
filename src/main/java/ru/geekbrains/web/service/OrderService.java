package ru.geekbrains.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.web.dtos.OrderDetailsDTO;
import ru.geekbrains.web.dtos.OrderItemDTO;
import ru.geekbrains.web.exceptions.ResourceNotFoundException;
import ru.geekbrains.web.model.Order;
import ru.geekbrains.web.model.OrderItem;
import ru.geekbrains.web.model.Product;
import ru.geekbrains.web.model.User;
import ru.geekbrains.web.repositories.OrderItemsRepository;
import ru.geekbrains.web.repositories.OrderRepository;
import ru.geekbrains.web.utils.Cart;

import javax.transaction.Transactional;
import java.security.Principal;
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
    public void createOrder(Principal principal, OrderDetailsDTO orderDetailsDTO) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));

        Cart cart = cartService.getCartForCurrentUser(principal, null);
        Order order = new Order();
        order.setUser(user);
        order.setAddress(orderDetailsDTO.getAddress());
        order.setPhone(orderDetailsDTO.getPhone());
        order.setTotalPrice(cart.getTotalPrice());
        order.setOrderName("Номер заказа: №" + order.getId());
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDTO i : cart.getItems()) {
            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(order);
            orderItem.setPrice(i.getPrice());
            orderItem.setPricePerProduct(i.getPricePerProduct());
            orderItem.setQuantity(i.getQuantity());
            Product product = productService.findById(i.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Продукт с выбранным id:" + i.getProductId() + "не существут"));
            orderItem.setProduct(product);
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        save(order);
        cartService.clearCart(principal, null);
    }

    public void save (Order order) {
        orderRepository.save(order);
    }

    public List<Order> findAllByUsername (String username) {
        return orderRepository.findAllByUsername(username);
    }
}
