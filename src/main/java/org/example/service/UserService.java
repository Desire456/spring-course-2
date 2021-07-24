package org.example.service;

import org.example.entity.User;

public interface UserService {
    User findByUserName(String username);
    boolean containsUser(String username);
    User validateUser(String username, String password);
}
