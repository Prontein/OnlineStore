package ru.geekbrains.web.core.integration;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.geekbrains.web.api.dtos.CartDto;


@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    @Value("${integration.cart-service.url}")
    private String cartServiceUrl;

    public CartDto getUserCartDto(String username) {
        CartDto cart = cartServiceWebClient.get()
                .uri("/api/v1/cart/0" + username)
                .header("username", username)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
        return cart;
    }

    public void clearUserCart(String username) {
        cartServiceWebClient.get()
                .uri("/api/v1/cart/0/clear")
                .header("username", username)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
