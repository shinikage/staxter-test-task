package com.staxter.repository;

import com.staxter.exception.repository.user.UserAlreadyExistsException;
import com.staxter.exception.repository.user.UserCreationException;
import com.staxter.model.domain.user.User;
import com.staxter.model.dto.user.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final HashMap<String, UserDto> repository;
    private final BCryptPasswordEncoder encoder;

    private UserRepositoryImpl(BCryptPasswordEncoder encoder) {
        this.repository = new HashMap<>();
        this.encoder = encoder;
    }

    @Override
    public UserDto create(User user) throws UserCreationException {

        if (user == null || StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            throw new UserCreationException("Cannot create user! User object itself or " +
                    "username or password could be null");
        }

        Collection<String> roles = user.getRoles();

        if (roles == null) {
            roles = new ArrayList<>();
            roles.add("USER");
        }

        synchronized (this) {
            if (repository.containsKey(user.getUserName())) {
                throw new UserAlreadyExistsException("User with username  " + user.getUserName() + " already exists!");
            }

            UserDto newUser = new UserDto(
                    repository.size() + 1,
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUserName(),
                    encoder.encode(user.getPassword()),
                    roles
            );

            repository.put(user.getUserName(), newUser);
            return newUser;
        }
    }

    @Override
    public synchronized UserDto get(String username) {
        return repository
                .entrySet()
                .stream()
                .filter(entry -> Objects.equals(username, entry.getValue().getUserName()))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(null);
    }
}
