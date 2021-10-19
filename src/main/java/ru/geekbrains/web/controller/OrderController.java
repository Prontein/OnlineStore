package ru.geekbrains.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.web.dtos.OrderDTO;
import ru.geekbrains.web.dtos.OrderDetailsDTO;
import ru.geekbrains.web.service.OrderService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDetailsDTO orderDetailsDTO, Principal principal) {
        orderService.createOrder(principal, orderDetailsDTO);
    }

    @GetMapping
    public List<OrderDTO> getOrdersForCurrentUser(Principal principal) {
        return orderService.findAllByUsername(principal.getName()).stream().map(OrderDTO::new).collect(Collectors.toList());
    }
}
