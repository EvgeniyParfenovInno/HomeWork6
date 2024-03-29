package ru.demo.limit.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.ap.internal.util.Strings;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.demo.limit.api.PaymentCreatureRequest;
import ru.demo.limit.api.PaymentResponse;
import ru.demo.limit.exception.OperationException;
import ru.demo.limit.exception.ValidationException;
import ru.demo.limit.mapping.PaymentMapper;
import ru.demo.limit.model.Payment;
import ru.demo.limit.model.payment.PaymentStatus;
import ru.demo.limit.service.LimitService;
import ru.demo.limit.service.PaymentService;
import ru.demo.limit.service.ProcessingService;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ProcessingServiceImpl implements ProcessingService {

    private final LimitService limitService;
    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper = Mappers.getMapper(PaymentMapper.class);

    @Override
    @Transactional
    public PaymentResponse createPayment(Long userId, PaymentCreatureRequest request) {
        validateRequest(request);

        var currentLimit = getCurrentLimit(userId);
        if (request.getAmount().compareTo(currentLimit) < 0)
            throw new OperationException("121", "Превышение допустимого лимита");

        var payment = paymentService.createPayment(paymentMapper.mapToModelFromDto(request, userId));
        return paymentMapper.mapToDtoFromModel(payment, getCurrentLimit(userId));
    }

    @Override
    public PaymentResponse acceptPayment(Long userId, String paymentId) {
        var payment = paymentService.acceptPayment(paymentId);
        return paymentMapper.mapToDtoFromModel(payment, getCurrentLimit(userId));
    }

    @Override
    public PaymentResponse cancelPayment(Long userId, String paymentId) {
        var payment = paymentService.cancelPayment(paymentId);
        return paymentMapper.mapToDtoFromModel(payment, getCurrentLimit(userId));
    }

    private void validateRequest(PaymentCreatureRequest request) {
        if (request == null)
            throw new ValidationException("111", "Не найдены атрибуты платежа");

        if (Strings.isEmpty(request.getPaymentId()))
            throw new ValidationException("112", "Не задан идентификатор платежа");

        if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) > 0)
            throw new ValidationException("113", "Сумма платежа не корректна");
    }

    private BigDecimal getCurrentLimit(Long userId) {
        var dailyLimit = limitService.getLimitByUserId(userId);
        var paymentsSum = paymentService.getPaymentsSumByUserId(userId);
        return dailyLimit.getValue().subtract(paymentsSum);
    }
}
