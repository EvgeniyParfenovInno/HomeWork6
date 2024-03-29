package ru.demo.limit.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentCreatureRequest {
    private final String paymentId;
    private final BigDecimal amount;
}
