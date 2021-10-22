package ru.geekbrains.web.api.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;


public class CommentDTO {
    private Long id;
    private String username;
    @NotNull(message = "Имя покупателя не указано")
    @Length(min = 3, max = 30, message = "Название продукта должно содержать 3 - 30 символов")
    private String consumer;
    @NotNull(message = "Невозможно отправить пустой отзыв")
    @Length(min = 3, max = 500, message = "Отзыв о продукте должен содержать 3 - 500 символов")
    private String content;

    public CommentDTO (Long productId, String consumer, String content) {
        this.id = productId;
        this.consumer = consumer;
        this.content = content;
    }

    public CommentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
