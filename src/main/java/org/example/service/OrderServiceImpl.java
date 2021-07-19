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
import org.example.exception.UserNotFoundException;
import org.example.repository.OrderRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;

    @Override
    public Order register(OrderRequestDto orderRequestDto) {
        User user;
        //store not encoded password bad practice, but it simple case for learning other things
        if (userRepository.containsUser(orderRequestDto.getUserName(), orderRequestDto.getPassword())) {
            //we already check existing of record, but of course that case has the meaning
            user = userRepository.findByUserName(orderRequestDto.getUserName())
                    .orElseThrow(ItemNotFoundException::new);
        } else {
            throw new UserNotFoundException();
        }

        return orderRepository.save(OrderMapper.toEntity(orderRequestDto, user.getId()))
                .orElseThrow(ItemNotFoundException::new);
    }

    @Override
    public OrderStatusResponseDto cancel(OrderStatusRequestDto orderStatusRequestDto) {
        if (!userRepository.containsUser(orderStatusRequestDto.getUserName(), orderStatusRequestDto.getPassword())) {
            throw new UserNotFoundException();
        }

        Order order = findById(orderStatusRequestDto.getOrderId());

        order.setOrderStatus(OrderStatuses.CANCELLED);
        Order updated = orderRepository.save(order)
                .orElseThrow(ItemNotFoundException::new);

        return OrderMapper.toOrderStatusResponseDto(updated);
    }

    @Override
    public OrderStatusResponseDto getOrderStatus(OrderStatusRequestDto orderStatusRequestDto) {
        return OrderMapper.toOrderStatusResponseDto(
                findById(orderStatusRequestDto.getOrderId())
        );
    }

    @Override
    public Order findById(long id) {
        return orderRepository.findByOrderId(id)
                .orElseThrow(ItemNotFoundException::new);
    }
}
