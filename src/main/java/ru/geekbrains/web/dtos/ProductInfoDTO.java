package ru.geekbrains.web.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.geekbrains.web.model.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ProductInfoDTO {
    private Long id;

//    @NotNull(message = "Имя покупателя не указано")
//    @Length(min = 3, max = 30, message = "Название продукта должно содержать 3 - 30 символов")
    private String username;
//    private String consumer;

//    @NotNull(message = "Невозможно отправить пустой отзыв")
//    @Length(min = 3, max = 500, message = "Отзыв о продукте должен содержать 3 - 500 символов")
    private boolean status;
//    private String content;


    @NotNull(message = "Введите название продукта")
    @Length(min = 3, max = 255, message = "Название продукта должно содержать 3 - 255 символов")
    private String title;

    @Min(value = 1, message = "Минимальная цена продукта не может быть менее 1")
    private int price;

    private List<CommentDTO> comments;

    public ProductInfoDTO(Product product, String username, boolean status) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.comments = product.getProductComments().stream().map(CommentDTO::new).collect(Collectors.toList());
        this.username = username;
        this.status = status;
    }
}
