package com.staxter.model.api.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginUserRequestModel {

    private String userName, password;
}
