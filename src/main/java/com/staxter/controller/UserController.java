package com.staxter.controller;

import com.staxter.model.api.CodeDescriptionResponseModel;
import com.staxter.model.api.user.RegisterUserRequestModel;
import com.staxter.model.api.user.RegisterUserResponseModel;
import com.staxter.model.api.user.LoginUserRequestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/userservice")
public interface UserController {

    @PostMapping("/register")
    ResponseEntity<RegisterUserResponseModel> register(@RequestBody RegisterUserRequestModel user);

    @PostMapping(value = "/login")
    ResponseEntity<CodeDescriptionResponseModel> login(@RequestBody LoginUserRequestModel user);
}
