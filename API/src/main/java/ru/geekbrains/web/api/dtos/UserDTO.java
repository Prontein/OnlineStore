package ru.geekbrains.web.api.dtos;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;


public class UserDTO {
    private Long id;
    @NotNull(message = "Введите имя пользователя")
    @Length(min = 2, max = 255, message = "Имя пользователя должно содержать 2 - 255 символов")
    private String username;

    @NotNull(message = "Введите пароль")
    @Length(min = 3, max = 255, message = "Пароль пользователя должен содержать 3 - 255 символов")
    private String password;

    @NotNull(message = "Введите электронную почту")
    private String email;

    @NotNull(message = "Введите имя")
    @Length(min = 2, max = 255, message = "Введите корректное имя")
    private String firstName;

    @NotNull(message = "Введите фамилию")
    @Length(min = 2, max = 255, message = "Введите корректную фамилию")
    private String lastName;

    public UserDTO() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserDTO(Long id, String username, String password, String email, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

