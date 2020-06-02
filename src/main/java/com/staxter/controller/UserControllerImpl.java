package com.staxter.controller;

import com.staxter.exception.repository.user.UserAlreadyExistsException;
import com.staxter.exception.repository.user.UserCreationException;
import com.staxter.model.api.CodeDescriptionResponseModel;
import com.staxter.model.api.user.RegisterUserRequestModel;
import com.staxter.model.api.user.RegisterUserResponseModel;
import com.staxter.model.api.user.LoginUserRequestModel;
import com.staxter.service.UserService;
import com.staxter.util.MapperUtil;
import com.staxter.util.ResponseCode;
import com.staxter.util.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

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

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    public ResponseEntity<CodeDescriptionResponseModel> userAlreadyExists(UserAlreadyExistsException ex, WebRequest request) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new CodeDescriptionResponseModel(
                                ResponseCode.USER_ALREADY_EXISTS.getValue(),
                                ResponseCode.USER_ALREADY_EXISTS_DESCRIPTION.getValue()
                        )
                );
    }

    @ExceptionHandler(value = {UserCreationException.class})
    public ResponseEntity<CodeDescriptionResponseModel> userCannotBeCreated(UserCreationException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new CodeDescriptionResponseModel(
                                ResponseCode.USER_CANNOT_BE_CREATED.getValue(),
                                ResponseCode.USER_CANNOT_BE_CREATED_DESCRIPTION.getValue()
                        )
                );
    }
}
