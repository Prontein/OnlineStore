package ru.geekbrains.web.cart.integration;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.geekbrains.web.api.dtos.ProductDTO;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final WebClient productServiceWebClient;

    @Value("${integration.product-service.url}")
    private String productServiceUrl;

    public ProductDTO getProductById(Long productId) {
        ProductDTO product = productServiceWebClient.get()
                .uri("/api/v1/products/" + productId)
                .retrieve()
                .bodyToMono(ProductDTO.class)
                .block();
        return product;
    }
}
