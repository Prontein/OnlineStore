package ru.geekbrains.web.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.web.api.dtos.CommentDTO;
import ru.geekbrains.web.api.exceptions.ResourceNotFoundException;
import ru.geekbrains.web.core.model.Comment;
import ru.geekbrains.web.core.model.Product;
import ru.geekbrains.web.core.repositories.CommentRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ProductService productService;

    @Transactional
    public void createComment(CommentDTO commentDTO) {
        Product product = productService.findById(commentDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Product id = " + commentDTO.getId() + " not found"));

        Comment comment = new Comment(commentDTO.getConsumer(), commentDTO.getContent(), commentDTO.getUsername());
        comment.setProduct(product);

        commentRepository.save(comment);
    }
}
