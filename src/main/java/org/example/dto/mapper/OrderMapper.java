package org.example.dto.mapper;

import org.example.dto.OrderRequestDto;
import org.example.dto.OrderStatusResponseDto;
import org.example.entity.Order;
import org.example.entity.OrderStatuses;

public class OrderMapper {
    public static Order toEntity(OrderRequestDto dto, long userId) {
        return new Order(0, userId, dto.getOrderNumber(), dto.getAmount(),
                dto.getCurrency(), dto.getReturnUrl(), dto.getFailUrl(),
                OrderStatuses.NEW);
    }

    public static OrderStatusResponseDto toOrderStatusResponseDto(Order order) {
        return new OrderStatusResponseDto(order.getOrderStatus(),
                order.getOrderNumber(),
                order.getAmount(),
                order.getCurrency());
    }
}
