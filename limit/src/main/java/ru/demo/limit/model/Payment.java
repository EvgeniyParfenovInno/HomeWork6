package ru.demo.limit.model;

import lombok.Data;
import ru.demo.limit.model.payment.PaymentStatus;

import java.math.BigDecimal;

@Data
public class Payment {
    private Long userId;
    private String paymentId;
    private BigDecimal amount;
    private PaymentStatus status;
}
