package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderStatusRequestDto {
    private String userName;
    private String password;
    private long orderId;
    private String language;
}
