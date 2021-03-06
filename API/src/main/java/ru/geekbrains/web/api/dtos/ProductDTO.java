package ru.geekbrains.web.api.dtos;


import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


public class ProductDTO {
    private Long id;

    @NotNull(message = "Введите название продукта")
    @Length(min = 3, max = 255, message = "Название продукта должно содержать 3 - 255 символов")
    private String title;

    @Min(value = 1, message = "Минимальная цена продукта не может быть менее 1")
    private BigDecimal price;

    public ProductDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductDTO(Long id, String title, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
}
