package ru.geekbrains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProdServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ProdServiceApp.class, args);
    }
}
