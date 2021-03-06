package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.OrderRequestDto;
import org.example.dto.OrderStatusRequestDto;
import org.example.dto.OrderStatusResponseDto;
import org.example.dto.mapper.OrderMapper;
import org.example.entity.Order;
import org.example.entity.OrderStatuses;
import org.example.entity.User;
import org.example.exception.ItemNotFoundException;
import org.example.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private UserService userService;

    @Override
    public Order register(OrderRequestDto orderRequestDto) {
        User user = userService.validateUser(orderRequestDto.getUserName(), orderRequestDto.getPassword());

        return orderRepository.save(OrderMapper.toEntity(orderRequestDto, user.getId()))
                .orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public OrderStatusResponseDto cancel(OrderStatusRequestDto orderStatusRequestDto) {
        User user = userService.validateUser(orderStatusRequestDto.getUserName(), orderStatusRequestDto.getPassword());

        Order order = orderRepository.findByIdAndUser(orderStatusRequestDto.getOrderId(), user)
                .orElseThrow(ItemNotFoundException::new);

        order.setOrderStatus(OrderStatuses.CANCELLED);
        Order updated = orderRepository.save(order)
                .orElseThrow(ItemNotFoundException::new);

        return OrderMapper.toOrderStatusResponseDto(updated);
    }

    @Override
    public OrderStatusResponseDto getOrderStatus(OrderStatusRequestDto orderStatusRequestDto) {
        User user = userService.validateUser(orderStatusRequestDto.getUserName(), orderStatusRequestDto.getPassword());

        Order order = orderRepository.findByIdAndUser(orderStatusRequestDto.getOrderId(), user)
                .orElseThrow(ItemNotFoundException::new);
        return OrderMapper.toOrderStatusResponseDto(order);
    }

    @Override
    public Order findById(long id) {
        return orderRepository.findById(id)
                .orElseThrow(ItemNotFoundException::new);
    }
}
