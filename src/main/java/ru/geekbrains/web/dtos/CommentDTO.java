package ru.geekbrains.web.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.geekbrains.web.model.Comment;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String username;
    @NotNull(message = "Имя покупателя не указано")
    @Length(min = 3, max = 30, message = "Название продукта должно содержать 3 - 30 символов")
    private String consumer;
    @NotNull(message = "Невозможно отправить пустой отзыв")
    @Length(min = 3, max = 500, message = "Отзыв о продукте должен содержать 3 - 500 символов")
    private String content;

    public CommentDTO (Comment comments) {
        this.id = comments.getId();
        this.consumer = comments.getConsumer();
        this.content = comments.getContent();
    }

}
