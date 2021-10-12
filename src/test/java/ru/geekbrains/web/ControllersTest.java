package ru.geekbrains.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.web.model.Order;
import ru.geekbrains.web.model.OrderItem;
import ru.geekbrains.web.model.User;
import ru.geekbrains.web.repositories.OrderRepository;
import ru.geekbrains.web.repositories.UserRepository;
import ru.geekbrains.web.service.OrderService;
import org.springframework.security.test.context.support.WithMockUser;
import ru.geekbrains.web.service.UserService;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ControllersTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderService orderService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    @WithMockUser(username = "user", roles = "USER")
    public void getAllOrders() throws Exception {

        List<OrderItem> testItems = new ArrayList<>();
        Order testOrderFirst = new Order();
        testOrderFirst.setId(1L);
        testOrderFirst.setUser(userRepository.getById(1L));
        testOrderFirst.setAddress("test_address_first");
        testOrderFirst.setPhone("123");
        testOrderFirst.setTotalPrice(1000);
        testOrderFirst.setOrderName("Номер заказа: № 1");
        testOrderFirst.setOrderItems(testItems);

        List<Order> allOrders = new ArrayList<>(Arrays.asList(
                testOrderFirst
        ));

        given(orderService.findAllByUsername("user")).willReturn(allOrders);

        System.out.println(allOrders);

        mvc
                .perform(
                        get("/api/v1/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)));
    }

}
