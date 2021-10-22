package ru.geekbrains.web.api.dtos;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDTO {
    private Long id;
    private List<OrderItemDTO> items;
    private String address;
    private String phone;
    private int price;
    private String orderName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public OrderDTO() {
    }

    public OrderDTO (Long Id, List<OrderItemDTO> items, String address, String phone, int totalPrice, String orderName) {
        this.id = id;
        this.items = items;
        this.address = address;
        this.phone = phone;
        this.price = totalPrice;
        this.orderName = orderName;
    }
}
