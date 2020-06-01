package com.staxter.model.api.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterUserResponseModel {

    private long id;
    private String firstName;
    private String lastName;
    private String userName;
}
