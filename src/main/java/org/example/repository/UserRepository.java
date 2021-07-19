package org.example.repository;

import org.example.entity.User;

import java.util.Optional;

public interface UserRepository {
    boolean containsUser(String userName, String password);
    Optional<User> findByUserName(String username);
}
