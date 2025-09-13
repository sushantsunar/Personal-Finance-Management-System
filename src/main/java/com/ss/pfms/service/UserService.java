package com.ss.pfms.service;

import com.ss.pfms.entity.User;
import com.ss.pfms.repository.UserRepository;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String email);
}