package ru.geekbrains.web.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.web.model.Order;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private List<OrderItemDTO> items;
    private String address;
    private String phone;
    private int price;
    private String orderName;

    public OrderDTO (Order order) {
        this.id = order.getId();
        this.items = order.getOrderItems().stream().map(OrderItemDTO::new).collect(Collectors.toList());
        this.address = order.getAddress();
        this.phone = order.getPhone();
        this.price = order.getTotalPrice();
        this.orderName = order.getOrderName();
    }
}
