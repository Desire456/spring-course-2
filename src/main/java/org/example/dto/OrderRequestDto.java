package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderRequestDto {
    private String userName;
    private String password;
    private String orderNumber;
    private int amount;
    private int currency;
    private String returnUrl;
    private String failUrl;
}
