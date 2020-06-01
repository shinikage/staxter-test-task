package com.staxter.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDto {

    private long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String hashedPassword;

    private Collection<String> roles;
}
