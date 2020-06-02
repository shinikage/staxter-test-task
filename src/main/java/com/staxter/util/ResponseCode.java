package com.staxter.util;

public enum ResponseCode {

    USER_ALREADY_EXISTS("USER_ALREADY_EXISTS"),
    USER_ALREADY_EXISTS_DESCRIPTION("A user with the given username already exists"),

    USER_CANNOT_BE_CREATED("USER_CANNOT_BE_CREATED"),
    USER_CANNOT_BE_CREATED_DESCRIPTION("User cannot be created. Something went wrong"),

    USER_LOGGED_IN_SUCCESSFULLY("USER_LOGGED_IN_SUCCESSFULLY"),
    USER_LOGGED_IN_SUCCESSFULLY_DESCRIPTION("User has received an authorization token and logged in successfully"),

    UNAUTHORIZED("UNAUTHORIZED"),
    UNAUTHORIZED_DESCRIPTION("Request cannot be authorized."),

    BAD_CREDENTIALS("BAD_CREDENTIALS"),
    BAD_CREDENTIALS_DESCRIPTION("Please, check the credentials. Login or password could be wrong."),
    ;

    private final String value;

    ResponseCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
