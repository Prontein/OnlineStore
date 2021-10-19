package ru.geekbrains.web.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.geekbrains.web.model.Product;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Long id;

    @NotNull(message = "Введите название продукта")
    @Length(min = 3, max = 255, message = "Название продукта должно содержать 3 - 255 символов")
    private String title;

    @Min(value = 1, message = "Минимальная цена продукта не может быть менее 1")
    private int price;

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
    }
}
