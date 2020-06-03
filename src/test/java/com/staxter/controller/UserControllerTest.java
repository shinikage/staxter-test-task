package com.staxter.controller;

import com.staxter.Application;
import com.staxter.exception.repository.user.UserAlreadyExistsException;
import com.staxter.model.api.CodeDescriptionResponseModel;
import com.staxter.model.api.user.LoginUserRequestModel;
import com.staxter.model.api.user.RegisterUserRequestModel;
import com.staxter.model.api.user.RegisterUserResponseModel;
import com.staxter.util.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {Application.class})
@TestPropertySource(locations = "classpath:test.properties")
@Slf4j
public class UserControllerTest {

    @Autowired
    private UserController userController;

    private static final RegisterUserRequestModel NON_EXISTING_USER = new RegisterUserRequestModel("N", "N", "non", "non");
    private static final RegisterUserRequestModel A = new RegisterUserRequestModel("A", "A", "a", "a");
    private static final RegisterUserRequestModel B = new RegisterUserRequestModel("B", "B", "b", "b");
    private static final RegisterUserRequestModel C = new RegisterUserRequestModel("C", "C", "c", "c");
    private static final RegisterUserRequestModel D = new RegisterUserRequestModel("D", "D", "d", "d");
    private static final RegisterUserRequestModel E = new RegisterUserRequestModel("E", "E", "e", "e");

    @Test
    public void login_withNoUsers_shouldThrowBadCredentialsException() {
        //arrange
        LoginUserRequestModel user = new LoginUserRequestModel(NON_EXISTING_USER.getUserName(),
                NON_EXISTING_USER.getPassword());
        //action
        try {
            userController.login(user);
        } catch (BadCredentialsException ex) {
            log.info(ex.getMessage(), ex);
            return;
        }
        //assert
        Assertions.fail();
    }

    @Test
    public void login_withWrongUsername_shouldThrowBadCredentialsException() {
        //arrange
        ResponseEntity<RegisterUserResponseModel> response = userController.register(A);
        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null || response.getBody().getId() <= 0) {
            Assertions.fail();
        }
        //action
        try {
            userController.login(new LoginUserRequestModel(A.getUserName() + "wrong", A.getPassword()));
        } catch (BadCredentialsException ex) {
            log.info(ex.getMessage(), ex);
            return;
        }
        //assert
        Assertions.fail();
    }

    @Test
    public void login_withWrongPassword_shouldThrowBadCredentialsException() {
        //arrange
        ResponseEntity<RegisterUserResponseModel> response = userController.register(B);
        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null || response.getBody().getId() <= 0) {
            Assertions.fail();
        }
        //action
        try {
            userController.login(new LoginUserRequestModel(B.getUserName(), B.getPassword() + "wrong"));
        } catch (BadCredentialsException ex) {
            log.info(ex.getMessage(), ex);
            return;
        }
        //assert
        Assertions.fail();
    }

    @Test
    public void login_withValidCredentials_shouldLoginSuccessfully() {
        //arrange
        ResponseEntity<RegisterUserResponseModel> response = userController.register(C);
        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null || response.getBody().getId() <= 0) {
            Assertions.fail();
        }
        //action
        ResponseEntity<CodeDescriptionResponseModel> loginResponse =
                userController.login(new LoginUserRequestModel(C.getUserName(), C.getPassword()));
        //assert
        Assertions.assertEquals(HttpStatus.OK, loginResponse.getStatusCode());
        Assertions.assertEquals(ResponseCode.USER_LOGGED_IN_SUCCESSFULLY.getValue(),
                loginResponse.getBody().getCode());
        Assertions.assertEquals(ResponseCode.USER_LOGGED_IN_SUCCESSFULLY_DESCRIPTION.getValue(),
                loginResponse.getBody().getDescription());
    }

    @Test
    public void register_withValidCredentials_shouldRegisterSuccessfully() {
        //arrange
        //action
        ResponseEntity<RegisterUserResponseModel> response = userController.register(D);
        //assert
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(D.getFirstName(), response.getBody().getFirstName());
        Assertions.assertEquals(D.getLastName(), response.getBody().getLastName());
        Assertions.assertEquals(D.getUserName(), response.getBody().getUserName());
        Assertions.assertTrue(response.getBody().getId() > 0);
    }

    @Test
    public void register_withAlreadyExistingUser_shouldThrowAnException() {
        //arrange
        ResponseEntity<RegisterUserResponseModel> response = userController.register(E);
        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null || response.getBody().getId() <= 0) {
            Assertions.fail();
        }
        //action
        try {
            userController.register(E);
        } catch (UserAlreadyExistsException ex) {
            log.info(ex.getMessage(), ex);
            return;
        }
        //assert
        Assertions.fail();
    }
}
