package ru.geekbrains.web.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.web.api.dtos.OrderDTO;
import ru.geekbrains.web.api.dtos.OrderDetailsDTO;
import ru.geekbrains.web.core.service.OrderService;
import ru.geekbrains.web.core.utils.Converter;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final Converter converter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDetailsDTO orderDetailsDTO, @RequestHeader String username) {
        orderService.createOrder(username, orderDetailsDTO);
    }

    @GetMapping
    public List<OrderDTO> getOrdersForCurrentUser(@RequestHeader String username) {
        return orderService.findAllByUsername(username).stream().map(o -> converter.orderToDto(o)).collect(Collectors.toList());
    }
}
