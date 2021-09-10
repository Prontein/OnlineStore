package ru.geekbrains.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.web.service.CartService;
import ru.geekbrains.web.utils.Cart;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public Cart showCart() {
        return cartService.getCartForCurrentUser();
    }

    @GetMapping("/add/{productId}")
    public void addToCart(@PathVariable Long productId) {
        cartService.addItem(productId);
    }

    @GetMapping("/decrement/{productId}")
    public void decrementItem(@PathVariable Long productId) {
        cartService.decrementItem(productId);
    }

    @GetMapping("/remove/{productId}")
    public void removeItem(@PathVariable Long productId) {
        cartService.removeItem(productId);
    }

}

