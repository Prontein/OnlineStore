package ru.geekbrains.web.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.geekbrains.web.model.User;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
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

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
    }
}

