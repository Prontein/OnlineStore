package ru.geekbrains.web.api.dtos;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;


public class ProductInfoDTO {
    private Long id;

    private String username;

    private boolean status;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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
    private BigDecimal price;

    private List<CommentDTO> comments;

    public ProductInfoDTO(Long productId, String productTitle, BigDecimal price, List<CommentDTO> comments, String username, boolean status) {
        this.id = productId;
        this.title = productTitle;
        this.price = price;
        this.comments = comments;
        this.username = username;
        this.status = status;
    }
}
