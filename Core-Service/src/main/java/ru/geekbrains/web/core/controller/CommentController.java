package ru.geekbrains.web.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.web.api.dtos.CommentDTO;
import ru.geekbrains.web.api.dtos.ProductInfoDTO;
import ru.geekbrains.web.core.exceptions.DataValidationException;
import ru.geekbrains.web.api.exceptions.ResourceNotFoundException;
import ru.geekbrains.web.core.model.*;
import ru.geekbrains.web.core.service.CommentService;
import ru.geekbrains.web.core.service.OrderService;
import ru.geekbrains.web.core.service.ProductService;
import ru.geekbrains.web.core.utils.Converter;

import java.security.Principal;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CommentController {
    private final CommentService commentService;
    private final OrderService orderService;
    private final ProductService productService;
    private final Converter converter;

    @GetMapping("/{id}")
    public ProductInfoDTO productInfoMessage(@PathVariable Long id, Principal principal) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found"));

        Optional<Order> orders = orderService.findAllByUsername(principal.getName()).stream().
                filter(order -> order.getOrderItems().stream().anyMatch(orderItem -> orderItem.getProduct().equals(product))).findAny();

        Optional<Product> ProductWithComment = productService.findByIdWithComment(id/*, principal.getName()*/).stream().
                filter(product1 -> product1.getProductComments().stream().anyMatch(comment -> comment.getUsername().equals(principal.getName()))).findAny();

        boolean isCommentAvailable = ProductWithComment.isEmpty() & orders.isPresent();

        return converter.productInfoDTO(product, principal.getName(), isCommentAvailable);
    }

    @PostMapping
    public void addComment(@RequestBody @Validated CommentDTO commentDTO, BindingResult bindingResult) {
        System.out.println(commentDTO);
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        commentService.createComment(commentDTO);
    }
}
