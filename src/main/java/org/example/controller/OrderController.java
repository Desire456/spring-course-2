package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.OrderRequestDto;
import org.example.dto.OrderStatusRequestDto;
import org.example.dto.OrderStatusResponseDto;
import org.example.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest")
@AllArgsConstructor
public class OrderController {
    private OrderService orderService;

    @PostMapping("/register.do")
    @ResponseBody
    public void register(OrderRequestDto orderRequestDto) {
        orderService.register(orderRequestDto);
    }

    @PostMapping("/reverse.do")
    @ResponseBody
    public OrderStatusResponseDto cancel(OrderStatusRequestDto orderStatusRequestDto) {
        return orderService.cancel(orderStatusRequestDto);
    }

    @PostMapping("/getOrderStatus.do")
    @ResponseBody
    public OrderStatusResponseDto getOrderStatus(OrderStatusRequestDto orderStatusRequestDto) {
        return orderService.getOrderStatus(orderStatusRequestDto);
    }
}
