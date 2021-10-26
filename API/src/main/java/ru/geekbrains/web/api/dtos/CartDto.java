package ru.geekbrains.web.api.dtos;

import java.util.List;

public class CartDto {
    private List<OrderItemDTO> items;
    private int totalPrice;

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartDto() {
    }

    public CartDto(List<OrderItemDTO> items, int totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }
}
