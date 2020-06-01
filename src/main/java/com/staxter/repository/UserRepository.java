package com.staxter.repository;

import com.staxter.exception.repository.user.UserCreationException;
import com.staxter.model.domain.user.User;
import com.staxter.model.dto.user.UserDto;

public interface UserRepository {

    UserDto create(User user) throws UserCreationException;

    UserDto get(String username);
}
