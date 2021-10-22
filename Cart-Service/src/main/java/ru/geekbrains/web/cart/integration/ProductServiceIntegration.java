package ru.geekbrains.web.cart.integration;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.web.api.dtos.ProductDTO;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    @Value("${integration.product-service.url}")
    private String productServiceUrl;

    public ProductDTO getProductById(Long productId) {
        return restTemplate.getForObject(productServiceUrl + "/api/v1/products/" + productId, ProductDTO.class);
    }
}
