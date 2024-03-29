package ru.demo.limit.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class PaymentResponse {
    private final String paymentId;
    private final String paymentStatus;
    private final BigDecimal currentLimit;
}
