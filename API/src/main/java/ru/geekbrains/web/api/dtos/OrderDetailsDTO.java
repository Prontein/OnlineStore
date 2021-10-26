package ru.geekbrains.web.api.dtos;


public class OrderDetailsDTO {
    private String phone;
    private String address;

    public OrderDetailsDTO() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
