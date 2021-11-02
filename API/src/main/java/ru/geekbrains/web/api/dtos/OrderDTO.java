package ru.geekbrains.web.api.dtos;

import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {
    private Long id;
    private List<OrderItemDTO> items;
    private String address;
    private String phone;
    private BigDecimal price;
    private String orderName;
    private String status;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderDTO() {
    }

    public OrderDTO(String status) {
        this.status = status;
    }

    public OrderDTO (Long id, List<OrderItemDTO> items, String address, String phone, BigDecimal totalPrice, String orderName, String status) {
        this.id = id;
        this.items = items;
        this.address = address;
        this.phone = phone;
        this.price = totalPrice;
        this.orderName = orderName;
        this.status = status;
    }
}
