package ru.geekbrains.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.web.dtos.OrderDetailsDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {
    @Autowired
    private MockMvc mockMvc;


    // Как добиться получения ошибки 401, с пост запросом получаю 400 ошибки
    @Test
//    @WithMockUser(username = "user", roles = "USER")
    public void securityAccessDeniedTest() throws Exception {
        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
        mockMvc.perform(post("/api/v1/orders"/*, orderDetailsDTO)*/))
                .andDo(print())
//                .andExpect(status().isOk());
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    public void securityCheckUserTest() throws Exception {
        mockMvc.perform(get("/api/v1/orders"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    // Если делать запрос без юзера, то получаем NullPointerException из-за того что Principal равен null
    @Test
    public void securityCheckUserTest2() throws Exception {
        mockMvc.perform(get("/api/v1/orders"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
