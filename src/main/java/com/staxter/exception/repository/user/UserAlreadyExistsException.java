package com.staxter.exception.repository.user;

public class UserAlreadyExistsException extends UserCreationException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
