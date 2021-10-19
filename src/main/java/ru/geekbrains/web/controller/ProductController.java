package ru.geekbrains.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.web.dtos.OrderDTO;
import ru.geekbrains.web.dtos.ProductDTO;
import ru.geekbrains.web.dtos.ProductInfoDTO;
import ru.geekbrains.web.exceptions.DataValidationException;
import ru.geekbrains.web.exceptions.ResourceNotFoundException;
import ru.geekbrains.web.model.Product;
import ru.geekbrains.web.service.CommentService;
import ru.geekbrains.web.service.ProductService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CommentService commentService;

    @GetMapping
    public Page<ProductDTO> findAll(@RequestParam(defaultValue = "1", name = "p") int pageIndex, @RequestParam MultiValueMap<String, String> params) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return productService.findAll(pageIndex - 1, 10, params).map(ProductDTO::new);
    }

    @GetMapping("/{id}")

//    public ProductInfoDTO findById(@PathVariable Long id) {
//        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product id = " + id + " not found"));
//        return new ProductInfoDTO(product);
//    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping
    public ProductDTO newProduct(@RequestBody @Validated ProductDTO productDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        productService.createProduct(product);
        return new ProductDTO(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody ProductInfoDTO productInfoDTO) {
    }


}
