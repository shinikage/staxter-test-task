package com.staxter.service;

import com.staxter.model.api.user.RegisterUserRequestModel;
import com.staxter.model.domain.user.User;
import com.staxter.repository.UserRepository;
import com.staxter.util.MapperUtil;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(RegisterUserRequestModel user) {
        return MapperUtil.map(userRepository.create(MapperUtil.map(user)));
    }
}
