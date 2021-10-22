package ru.geekbrains.web.cart.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.web.api.dtos.CartDto;
import ru.geekbrains.web.api.dtos.ProductDTO;
import ru.geekbrains.web.api.dtos.StringResponse;
import ru.geekbrains.web.cart.integration.ProductServiceIntegration;
import ru.geekbrains.web.cart.services.CartService;
import ru.geekbrains.web.cart.utils.Cart;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;
    private final ProductServiceIntegration productServiceIntegration;

    @GetMapping("/generate")
    public StringResponse generateCartUuid() {
        return new StringResponse(cartService.generateCartUuid());
    }

    @GetMapping("/{uuid}/merge")
    public void mergeCarts(@RequestHeader String username, @PathVariable String uuid) {

        cartService.merge(
                getCurrentCartUuid(username, null),
                getCurrentCartUuid(null, uuid)
        );
    }

    @GetMapping("/{uuid}")
    public CartDto showCart(@RequestHeader(required = false) String username, @PathVariable String uuid) {
        Cart cart = cartService.getCurrentCart(getCurrentCartUuid(username, uuid));
        return new CartDto(cart.getItems(), cart.getTotalPrice());
    }

    @GetMapping("/{uuid}/add/{productId}")
    public void addToCart(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {
        ProductDTO product = productServiceIntegration.getProductById(productId);
        cartService.addToCart(getCurrentCartUuid(username, uuid), product);
    }

    @GetMapping("/{uuid}/decrement/{productId}")
    public void decrementItem(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.decrementItem(getCurrentCartUuid(username, uuid), productId);
    }

    @GetMapping("/{uuid}/remove/{productId}")
    public void removeItem(@RequestHeader(required = false) String username, @PathVariable String uuid, @PathVariable Long productId) {
        cartService.removeItemFromCart(getCurrentCartUuid(username, uuid), productId);
    }

    private String getCurrentCartUuid(String username, String uuid) {
        if (username != null) {
            return cartService.getCartUuidFromSuffix(username);
        }
        return cartService.getCartUuidFromSuffix(uuid);
    }
}

