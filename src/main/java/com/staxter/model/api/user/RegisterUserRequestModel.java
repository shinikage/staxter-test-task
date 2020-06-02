package com.staxter.model.api.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterUserRequestModel {

    private final String firstName;
    private final String lastName;
    private final String userName;
    private final String password;
}
