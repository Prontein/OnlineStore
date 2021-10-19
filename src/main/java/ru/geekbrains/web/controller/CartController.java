package ru.geekbrains.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.web.dtos.StringResponse;
import ru.geekbrains.web.service.CartService;
import ru.geekbrains.web.utils.Cart;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/generate")
    public StringResponse generateCartUuid() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{uuid}/merge")
    public void mergeCarts(Principal principal, @PathVariable String uuid) {
        cartService.merge(
                getCurrentCartUuid(principal, null),
                getCurrentCartUuid(null, uuid)
        );
    }

    @GetMapping("/{uuid}")
    public Cart showCart(Principal principal, @PathVariable String uuid) {
        return cartService.getCurrentCart(getCurrentCartUuid(principal, uuid));
    }

    @GetMapping("/{uuid}/add/{productId}")
    public void addToCart(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.addToCart(getCurrentCartUuid(principal, uuid), productId);
    }

    @GetMapping("/{uuid}/decrement/{productId}")
    public void decrementItem(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.decrementItem(getCurrentCartUuid(principal, uuid), productId);
    }

    @GetMapping("/{uuid}/remove/{productId}")
    public void removeItem(Principal principal, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.removeItemFromCart(getCurrentCartUuid(principal, uuid), productId);
    }

    private String getCurrentCartUuid(Principal principal, String uuid) {
        if (principal != null) {
            return cartService.getCartUuidFromSuffix(principal.getName());
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }
}

