package ru.demo.limit.api;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentCreatureRequest {
    private final String paymentId;
    private final BigDecimal amount;
}
