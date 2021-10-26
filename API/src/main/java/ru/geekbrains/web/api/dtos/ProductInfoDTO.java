package ru.geekbrains.web.api.dtos;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


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


    public ProductInfoDTO() {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    @NotNull(message = "Введите название продукта")
    @Length(min = 3, max = 255, message = "Название продукта должно содержать 3 - 255 символов")
    private String title;

    @Min(value = 1, message = "Минимальная цена продукта не может быть менее 1")
    private int price;

    private List<CommentDTO> comments;

    public ProductInfoDTO(Long productId, String productTitle, int price, List<CommentDTO> comments, String username, boolean status) {
        this.id = productId;
        this.title = productTitle;
        this.price = price;
        this.comments = comments;
        this.username = username;
        this.status = status;
    }
}
