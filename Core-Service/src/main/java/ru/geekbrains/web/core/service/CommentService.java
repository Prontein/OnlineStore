package ru.geekbrains.web.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.web.api.dtos.CommentDTO;
import ru.geekbrains.web.api.dtos.ProductInfoDTO;
import ru.geekbrains.web.api.exceptions.ResourceNotFoundException;
import ru.geekbrains.web.core.model.Comment;
import ru.geekbrains.web.core.model.Order;
import ru.geekbrains.web.core.model.Product;
import ru.geekbrains.web.core.repositories.CommentRepository;
import ru.geekbrains.web.core.utils.Converter;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ProductService productService;
    private final OrderService orderService;
    private final Converter converter;

    @Transactional
    public ProductInfoDTO checkOrder (Long id, String username) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found"));

        Optional<Order> orders = orderService.findAllByUsername(username).stream().
                filter(order -> order.getOrderItems().stream().anyMatch(orderItem -> orderItem.getProduct().equals(product))).findAny();

        Optional<Product> ProductWithComment = productService.findByIdWithComment(id).stream().
                filter(product1 -> product1.getProductComments().stream().anyMatch(comment -> comment.getUsername().equals(username))).findAny();

        boolean isCommentAvailable = ProductWithComment.isEmpty() & orders.isPresent();

        return converter.productInfoDTO(product, username, isCommentAvailable);
    }

    @Transactional
    public void createComment(CommentDTO commentDTO) {
        Product product = productService.findById(commentDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Product id = " + commentDTO.getId() + " not found"));

        Comment comment = new Comment(commentDTO.getConsumer(), commentDTO.getContent(), commentDTO.getUsername());
        comment.setProduct(product);

        commentRepository.save(comment);
    }
}
