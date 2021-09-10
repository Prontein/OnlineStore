package ru.geekbrains.web.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDetailsDTO {
    private String phone;
    private String address;
}
