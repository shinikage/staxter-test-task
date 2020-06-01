package com.staxter.model.api.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterUserRequestModel {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
}
