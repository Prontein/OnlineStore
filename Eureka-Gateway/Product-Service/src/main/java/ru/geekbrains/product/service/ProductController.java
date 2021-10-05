package ru.geekbrains.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.common.ProductDTO;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class ProductController {
    private final ProductService productService;

    private static final Function<Product, ProductDTO> mapper = p -> new ProductDTO(p.getId(), p.getTitle(), p.getPrice());

    @GetMapping
    public List<ProductDTO> findAll() {
        return productService.findAll().stream().map(mapper).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return mapper.apply(productService.findById(id).get());
    }

}
