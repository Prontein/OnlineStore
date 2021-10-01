package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.common.ProductDTO;

import java.util.List;

@RestController
public class FrontController {
    @Autowired
    private RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @GetMapping("/products")
    public List<ProductDTO> showAll() {
        ResponseEntity restExchange = restTemplate.exchange("http://product-service/products", HttpMethod.GET, null, List.class);
        List<ProductDTO> dtos = (List<ProductDTO>)restExchange.getBody();
        return dtos;
    }
//
//    @GetMapping
//    public String show() {
//        return "Привет";
//    }
}
