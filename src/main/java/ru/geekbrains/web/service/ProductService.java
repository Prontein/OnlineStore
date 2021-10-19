package ru.geekbrains.web.service;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import ru.geekbrains.web.exceptions.ResourceNotFoundException;
import ru.geekbrains.web.model.Product;
import ru.geekbrains.web.repositories.ProductRepository;
import ru.geekbrains.web.repositories.specifications.ProductSpecifications;
import ru.geekbrains.web.soap.products.ProductSoapDTO;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private static final String FILTER_MIN_PRICE = "min_price";
    private static final String FILTER_MAX_PRICE = "max_price";
    private static final String FILTER_TITLE = "title";

    public Page<Product> findAll(int pageIndex, int pageSize, MultiValueMap<String,String> rqParams) {
        return productRepository.findAll(constructSpecification(rqParams), PageRequest.of(pageIndex, pageSize));
    }

    public List<Product> findAllInList() {
        return productRepository.findAll();
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

    private Specification<Product> constructSpecification(MultiValueMap<String, String> params) {
        Specification<Product> spec = Specification.where(null);
        if (params.containsKey(FILTER_MIN_PRICE) && !params.getFirst(FILTER_MIN_PRICE).isBlank()) {
            int minPrice = Integer.parseInt(params.getFirst(FILTER_MIN_PRICE));
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (params.containsKey(FILTER_MAX_PRICE) && !params.getFirst(FILTER_MAX_PRICE).isBlank()) {
            int maxPrice = Integer.parseInt(params.getFirst(FILTER_MAX_PRICE));
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
        }
        if (params.containsKey(FILTER_TITLE) && !params.getFirst(FILTER_TITLE).isBlank()) {
            String title = params.getFirst(FILTER_TITLE);
            spec = spec.and(ProductSpecifications.titleLike(title));
        }
        return spec;
    }

    public Optional<Product> findByIdWithComment(Long id/*, String username*/) {
        return productRepository.findByIdWithComments(id/*,username*/);
    }
}
