package com.staxter.service;

import com.staxter.model.api.user.RegisterUserRequestModel;
import com.staxter.model.domain.user.User;

public interface UserService {

    User create(RegisterUserRequestModel user);
}
