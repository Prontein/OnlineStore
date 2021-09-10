package ru.geekbrains.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.web.dtos.UserDTO;
import ru.geekbrains.web.exceptions.DataValidationException;
import ru.geekbrains.web.model.User;
import ru.geekbrains.web.service.UserService;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/registration")
    public UserDTO newUser(@RequestBody @Validated UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new DataValidationException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        }
        User user = userService.registerNewUserAccount(userDTO);
        return new UserDTO(user);
    }
}
