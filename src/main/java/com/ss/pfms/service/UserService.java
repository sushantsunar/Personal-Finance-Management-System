package com.ss.pfms.service;

import com.ss.pfms.entity.User;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> findByEmail(String email);
}