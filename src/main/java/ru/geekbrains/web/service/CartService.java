package ru.geekbrains.web.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.web.exceptions.ResourceNotFoundException;
import ru.geekbrains.web.model.Product;
import ru.geekbrains.web.utils.Cart;

import javax.annotation.PostConstruct;


@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private Cart cart;

    @PostConstruct
    public void init() {
        this.cart = new Cart();
    }

    public Cart getCartForCurrentUser () {
        return cart;
    }

    public void addItem(Long productId) {
        if (cart.add(productId)) {
            return;
        }
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Продукт с выбранным id:" + productId + "не существут"));
        cart.add(product);
    }

    public void removeItem(Long productId) {
        cart.remove(productId);
    }

    public void decrementItem(Long productId) {
        cart.decrement(productId);
    }

    public void clearCart() {
        cart.clear();
    }
}
