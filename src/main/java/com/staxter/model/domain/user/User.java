package com.staxter.model.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@AllArgsConstructor
@Data
public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String hashedPassword;
    private String password;

    private Collection<String> roles;
}
