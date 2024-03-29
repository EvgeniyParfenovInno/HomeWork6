package ru.demo.limit.service;

import ru.demo.limit.model.Payment;

import java.math.BigDecimal;
import java.util.Optional;

public interface PaymentService {
    Optional<Payment> getPayment(String paymentId);
    BigDecimal getPaymentsSumByUserId(Long userId);
    Payment createPayment(Payment payment);
    Payment acceptPayment(String paymentId);
    Payment cancelPayment(String paymentId);
    void clearPayments();
}
