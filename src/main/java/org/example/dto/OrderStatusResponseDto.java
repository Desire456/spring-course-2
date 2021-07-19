package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderStatusResponseDto {
    private String orderStatus;
    private String orderNumber;
    private int amount;
    private int currency;
}
