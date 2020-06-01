package com.staxter.controller;

import com.staxter.model.api.CodeDescriptionResponseModel;
import com.staxter.model.api.user.RegisterUserRequestModel;
import com.staxter.model.api.user.RegisterUserResponseModel;
import com.staxter.model.api.user.LoginUserRequestModel;
import com.staxter.service.UserService;
import com.staxter.util.MapperUtil;
import com.staxter.util.ResponseCode;
import com.staxter.util.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public ResponseEntity<RegisterUserResponseModel> register(@RequestBody RegisterUserRequestModel user) {
        return ResponseEntity.ok(MapperUtil.map(userService.create(user)));
    }

    @Override
    public ResponseEntity<CodeDescriptionResponseModel> login(@RequestBody LoginUserRequestModel user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUserName(),
                user.getPassword())
        );

        final String token = jwtTokenUtil.generateToken(user.getUserName());

        return ResponseEntity
                .ok()
                .header("Authorization", "Bearer " + token)
                .body(new CodeDescriptionResponseModel(
                        ResponseCode.USER_LOGGED_IN_SUCCESSFULLY.getValue(),
                        ResponseCode.USER_LOGGED_IN_SUCCESSFULLY_DESCRIPTION.getValue()
                ));
    }
}
