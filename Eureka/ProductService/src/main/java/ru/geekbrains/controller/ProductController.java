package ru.geekbrains.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.common.ProductDTO;

import ru.geekbrains.model.Product;
import ru.geekbrains.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private List<Product> productList = new ArrayList<>();

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/products")
    public List<ProductDTO> findAll() {

        return covertToDTO();
    }

    private List<ProductDTO> covertToDTO() {
        List<ProductDTO> listDTO = new ArrayList<>();
        productList = productRepository.findAll();
        for (Product prod : productList) {
            listDTO.add(new ProductDTO(prod.getId(), prod.getTitle(), prod.getPrice()));
        }
        return listDTO;
    }
}
