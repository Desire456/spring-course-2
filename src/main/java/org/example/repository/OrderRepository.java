package org.example.repository;

import org.example.entity.Order;
import org.example.entity.User;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> save(Order order);
    Optional<Order> findById(long id);
    Optional<Order> findByIdAndUser(long id, User user);
}
