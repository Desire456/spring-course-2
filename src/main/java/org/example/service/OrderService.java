package org.example.service;

import org.example.dto.OrderRequestDto;
import org.example.dto.OrderStatusRequestDto;
import org.example.dto.OrderStatusResponseDto;
import org.example.entity.Order;

public interface OrderService {
    Order register(OrderRequestDto orderRequestDto);
    OrderStatusResponseDto cancel(OrderStatusRequestDto orderStatusRequestDto);
    OrderStatusResponseDto getOrderStatus(OrderStatusRequestDto orderStatusRequestDto);
    Order findById(long id);
}
