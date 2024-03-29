package ru.demo.limit.service;

import ru.demo.limit.api.PaymentCreatureRequest;
import ru.demo.limit.api.PaymentResponse;

public interface ProcessingService {
    PaymentResponse createPayment(Long userId, PaymentCreatureRequest request);
    PaymentResponse acceptPayment(Long userId, String paymentId);
    PaymentResponse cancelPayment(Long userId, String paymentId);
}
