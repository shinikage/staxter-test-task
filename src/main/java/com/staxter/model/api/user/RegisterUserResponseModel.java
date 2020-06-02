package com.staxter.model.api.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterUserResponseModel {

    private final long id;
    private final String firstName;
    private final String lastName;
    private final String userName;
}
