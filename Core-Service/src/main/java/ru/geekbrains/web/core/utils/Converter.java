package ru.geekbrains.web.core.utils;

import org.springframework.stereotype.Component;
import ru.geekbrains.web.api.dtos.*;
import ru.geekbrains.web.core.model.Comment;
import ru.geekbrains.web.core.model.Order;
import ru.geekbrains.web.core.model.OrderItem;
import ru.geekbrains.web.core.model.Product;

import java.util.stream.Collectors;

@Component
public class Converter {
    public ProductDTO productToDto(Product product) {
        return new ProductDTO(product.getId(), product.getTitle(), product.getPrice());
    }

    public OrderItemDTO orderItemToDTO(OrderItem orderItem) {
        return new OrderItemDTO(orderItem.getProduct().getId(), orderItem.getProduct().getTitle(), orderItem.getQuantity(), orderItem.getPricePerProduct(), orderItem.getPrice());
    }

    public OrderDTO orderToDto(Order order) {
        return new OrderDTO(order.getId(), order.getOrderItems().stream().map(oi -> orderItemToDTO(oi)).collect(Collectors.toList()), order.getAddress(), order.getPhone(), order.getTotalPrice(), order.getOrderName(), order.getStatus());
    }

    public ProductInfoDTO productInfoDTO(Product product, String username, boolean status) {
        return new ProductInfoDTO(product.getId(), product.getTitle(), product.getPrice(), product.getProductComments().stream().map(c -> commentToDTO(c)).collect(Collectors.toList()), username, status);
    }

    public CommentDTO commentToDTO(Comment comment) {
        return new CommentDTO(comment.getProduct().getId(), comment.getConsumer(), comment.getContent());
    }

}
