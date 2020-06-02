package com.staxter.model.api.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginUserRequestModel {

    private final String userName;
    private final String password;
}
