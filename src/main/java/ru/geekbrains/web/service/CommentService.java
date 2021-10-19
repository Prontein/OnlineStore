package ru.geekbrains.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.web.dtos.CommentDTO;
import ru.geekbrains.web.exceptions.ResourceNotFoundException;
import ru.geekbrains.web.model.Comment;
import ru.geekbrains.web.model.Product;
import ru.geekbrains.web.repositories.CommentRepository;

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
