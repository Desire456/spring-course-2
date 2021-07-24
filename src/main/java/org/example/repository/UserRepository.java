package org.example.repository;

import org.example.entity.User;

import java.util.Optional;

public interface UserRepository {
    Boolean containsUser(String userName);
    Optional<User> findByUserName(String username);
}
