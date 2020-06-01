package com.staxter.exception.controller.advice;

import com.staxter.exception.repository.user.UserAlreadyExistsException;
import com.staxter.exception.repository.user.UserCreationException;
import com.staxter.model.api.CodeDescriptionResponseModel;
import com.staxter.util.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class UserControllerAdvice {

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<CodeDescriptionResponseModel> userIsNotFound(UsernameNotFoundException ex, WebRequest request) {
        return ResponseEntity.ok(new CodeDescriptionResponseModel(
                        ResponseCode.USER_IS_NOT_FOUND.getValue(),
                        ResponseCode.USER_IS_NOT_FOUND_DESCRIPTION.getValue()
                )
        );
    }


    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    public ResponseEntity<CodeDescriptionResponseModel> userAlreadyExists(UserAlreadyExistsException ex, WebRequest request) {
        return ResponseEntity.ok(new CodeDescriptionResponseModel(
                        ResponseCode.USER_ALREADY_EXISTS.getValue(),
                        ResponseCode.USER_ALREADY_EXISTS_DESCRIPTION.getValue()
                )
        );
    }

    @ExceptionHandler(value = {UserCreationException.class})
    public ResponseEntity<CodeDescriptionResponseModel> userCannotBeCreated(UserCreationException ex, WebRequest request) {
        return ResponseEntity.ok(
                new CodeDescriptionResponseModel(
                        ResponseCode.USER_CANNOT_BE_CREATED.getValue(),
                        ResponseCode.USER_CANNOT_BE_CREATED_DESCRIPTION.getValue()
                )
        );
    }
}