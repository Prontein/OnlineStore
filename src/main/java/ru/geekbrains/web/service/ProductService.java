package ru.geekbrains.web.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.web.dtos.ProductDTO;
import ru.geekbrains.web.exceptions.ResourceNotFoundException;
import ru.geekbrains.web.model.Product;
import ru.geekbrains.web.repositories.ProductRepository;
import ru.geekbrains.web.soap.products.ProductSoapDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public Optional<Product> findByTitle(String title) {
        return productRepository.findByTitle(title);
    }

    public static final Function<Product, ProductSoapDTO> functionEntityToSoap = prod -> {
        ProductSoapDTO prodSoap = new ProductSoapDTO();
        prodSoap.setId(prod.getId());
        prodSoap.setTitle(prod.getTitle());
        prodSoap.setPrice(prod.getPrice());
        return prodSoap;
    };

    public List<ProductSoapDTO> getAllProductsForSoap() {
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    public ProductSoapDTO findByTitleForSoap(String title) {
        return productRepository.findByTitle(title).map(functionEntityToSoap).orElseThrow(() -> new ResourceNotFoundException("ProductSoapDTO title = " + title + " not found"));
    }
}
