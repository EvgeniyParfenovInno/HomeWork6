package ru.demo.limit.api;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.demo.limit.model.payment.PaymentStatus;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class PaymentResponse {
    private final String paymentId;
    private final PaymentStatus paymentStatus;
    private final BigDecimal currentLimit;
}
