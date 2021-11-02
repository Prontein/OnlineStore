package ru.geekbrains.web.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.geekbrains.web.api.dtos.CartDto;
import ru.geekbrains.web.api.dtos.OrderDTO;
import ru.geekbrains.web.api.dtos.OrderDetailsDTO;
import ru.geekbrains.web.api.dtos.OrderItemDTO;
import ru.geekbrains.web.api.exceptions.ResourceNotFoundException;
import ru.geekbrains.web.core.integration.CartServiceIntegration;
import ru.geekbrains.web.core.model.Order;
import ru.geekbrains.web.core.model.OrderItem;
import ru.geekbrains.web.core.model.Product;
import ru.geekbrains.web.core.repositories.OrderRepository;
import ru.geekbrains.web.core.utils.Converter;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartServiceIntegration cartServiceIntegration;
    private final ProductService productService;
    private final Converter converter;
    private final OrderRepository orderRepository;

    @Transactional
    public Order createOrder(String username, OrderDetailsDTO orderDetailsDTO) {
        CartDto cart = cartServiceIntegration.getUserCartDto(username);
        Order order = new Order();
        order.setUsername(username);
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
        order.setStatus("PROCESSING");
        save(order);
        cartServiceIntegration.clearUserCart(username);
        return order;
    }

    public void save (Order order) {
        orderRepository.save(order);
    }



    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findAllByUsername (String username) {
        return orderRepository.findAllByUsername(username);
    }

   @Transactional
    public Optional<OrderDTO> findDtoByIdAndUsername(Long id, String username) {
        return orderRepository.findByIdAndUsername(id, username).map(o -> converter.orderToDto(o));
    }
}
