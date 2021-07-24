package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.User;
import org.example.exception.BadCredentialsException;
import org.example.exception.UserNotFoundException;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public boolean containsUser(String username) {
        return userRepository.containsUser(username);
    }

    @Override
    public User validateUser(String username, String password) {
        User user = findByUserName(username);
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            throw new BadCredentialsException();
        }
    }
}
