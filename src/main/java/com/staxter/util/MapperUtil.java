package com.staxter.util;

import com.staxter.model.api.user.RegisterUserRequestModel;
import com.staxter.model.api.user.RegisterUserResponseModel;
import com.staxter.model.domain.user.User;
import com.staxter.model.dto.user.UserDto;

public final class MapperUtil {

    public static RegisterUserResponseModel map(User user) {
        return new RegisterUserResponseModel(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUserName()
        );
    }

    public static User map(UserDto user) {
        return new User(user.getId(), user.getFirstName(), user.getLastName(), user.getUserName(),
                user.getHashedPassword(), null, user.getRoles());
    }

    public static User map(RegisterUserRequestModel user) {
        return new User(
                -1,
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                null,
                user.getPassword(),
                null
        );
    }
}
