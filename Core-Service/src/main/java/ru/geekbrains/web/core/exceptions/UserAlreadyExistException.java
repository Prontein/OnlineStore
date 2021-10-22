package ru.geekbrains.web.core.exceptions;

import lombok.Data;

@Data
public class UserAlreadyExistException extends RuntimeException{
    private String messages;

    public UserAlreadyExistException(String messages) {
        this.messages = messages;
    }
}

