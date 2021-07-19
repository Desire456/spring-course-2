package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private long id;
    private long userId;
    private String orderNumber;
    private int amount;
    private int currency;
    private String returnUrl;
    private String failUrl;
    private String orderStatus;
}