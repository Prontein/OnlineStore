package ru.geekbrains.web.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.web.dtos.ProductDTO;
import ru.geekbrains.web.exceptions.ResourceNotFoundException;
import ru.geekbrains.web.model.Product;
import ru.geekbrains.web.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> findAll(int pageIndex, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public void updateProduct(ProductDTO productDTO) {
        Product product = findById(productDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Product id = " + productDTO.getId() + " not found"));
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
    }
}
