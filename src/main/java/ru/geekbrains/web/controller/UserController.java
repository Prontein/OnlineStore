package ru.geekbrains.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.web.dtos.ProfileDto;
import ru.geekbrains.web.exceptions.ResourceNotFoundException;
import ru.geekbrains.web.model.User;
import ru.geekbrains.web.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ProfileDto aboutCurrentUser(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти пользователя. Имя пользователя: " + principal.getName()));
        return new ProfileDto(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail());
    }
}
