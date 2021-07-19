package org.example.repository;

import org.example.entity.Order;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> save(Order order);
    Optional<Order> findByOrderId(long orderId);
}
