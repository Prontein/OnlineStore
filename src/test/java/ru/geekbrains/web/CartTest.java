package ru.geekbrains.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.web.model.Product;
import ru.geekbrains.web.repositories.ProductRepository;
import ru.geekbrains.web.service.CartService;
import ru.geekbrains.web.service.ProductService;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Optional;

@SpringBootTest
//@ActiveProfiles("test")
public class CartTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductRepository productRepository;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void initTestCart () {
        cartService.clearCart(null, "test_cart");
    }

    @Test
    @Transactional
    public void addToCart() {
        Product testFirstProduct = productRepository.getById(1L);
        Product testSecondProduct= productRepository.getById(2L);

        Mockito.doReturn(Optional.of(testFirstProduct)).when(productService).findById(1L);
        Mockito.doReturn(Optional.of(testSecondProduct)).when(productService).findById(2L);

        cartService.addItem(null,"test_cart", 1L);
        cartService.addItem(null,"test_cart", 1L);

        cartService.addItem(null,"test_cart", 2L);

        Mockito.verify(productService, Mockito.times(1)).findById(ArgumentMatchers.eq(1L));
        Assertions.assertEquals(2, cartService.getCartByKey("test_cart").getItems().size());
    }

    @Test
    @Transactional
    public void incrementAndDecrementInCart() {
        Product testProduct = productRepository.getById(3L);

        Mockito.doReturn(Optional.of(testProduct)).when(productService).findById(3L);
        for (int i = 5; i > 0; i--) {
            cartService.addItem(null, "test_cart", 3L);
        }
        Assertions.assertEquals(5, cartService.getCartByKey("test_cart").getItems().stream().findFirst().get().getQuantity());

        for (int i = 4; i > 0; i--) {
            cartService.decrementItem(null, "test_cart", 3L);
        }
        Assertions.assertEquals(1, cartService.getCartByKey("test_cart").getItems().stream().findFirst().get().getQuantity());

        for (int i = 5; i > 0; i--) {
            cartService.decrementItem(null, "test_cart", 3L);
        }
        Assertions.assertEquals(0, cartService.getCartByKey("test_cart").getItems().size());

    }

    @Test
    @Transactional
    public void mergeCart() {

        Principal principal = () -> "John";
        cartService.clearCart(principal, "test_cartForMerge");

        cartService.getCartForCurrentUser(principal,"test_cartForMerge");

        Product testProduct = productRepository.getById(4L);
        Mockito.doReturn(Optional.of(testProduct)).when(productService).findById(4L);
        cartService.addItem(null,"test_cart", 4L);
        Assertions.assertEquals(1, cartService.getCartByKey("test_cart").getItems().size());

        Product testProductForMerge = productRepository.getById(5L);
        Mockito.doReturn(Optional.of(testProductForMerge)).when(productService).findById(5L);
        cartService.addItem(principal,"test_cartForMerge", 5L);
        Assertions.assertEquals(1, cartService.getCartByKey(principal.getName()).getItems().size());

        cartService.merge(principal, "test_cart");

        Assertions.assertEquals(2, cartService.getCartByKey(principal.getName()).getItems().size());
    }


}
