package ru.geekbrains.web.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.web.api.dtos.CommentDTO;
import ru.geekbrains.web.api.dtos.ProductInfoDTO;
import ru.geekbrains.web.core.exceptions.DataValidationException;
import ru.geekbrains.web.core.service.CommentService;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;


    @GetMapping("/{id}")
    public ProductInfoDTO productInfoMessage(@PathVariable Long id, @RequestHeader(required = false) String username) {
        return  commentService.checkOrder(id, username);
    }

    @PostMapping
    public void addComment(@RequestBody @Validated CommentDTO commentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        commentService.createComment(commentDTO);
    }
}
