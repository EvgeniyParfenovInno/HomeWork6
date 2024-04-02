package ru.demo.limit.service.impl;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.demo.limit.dto.MessageCode;
import ru.demo.limit.entity.PaymentEntity;
import ru.demo.limit.exception.EntityNotExistsException;
import ru.demo.limit.exception.OperationException;
import ru.demo.limit.mapping.PaymentMapper;
import ru.demo.limit.model.Payment;
import ru.demo.limit.model.payment.PaymentStatus;
import ru.demo.limit.repository.PaymentRepository;
import ru.demo.limit.service.PaymentService;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper = Mappers.getMapper(PaymentMapper.class);

    @Override
    public Optional<Payment> getPayment(String paymentId) {
        var entity = paymentRepository.findByPaymentId(paymentId).orElse(null);
        return Optional.ofNullable(mapper.mapToModelFromEntity(entity));
    }

    @Override
    public BigDecimal getPaymentsSumByUserId(Long userId) {
        // Переделать на запрос к базе
        return paymentRepository.findAllByUserId(userId).stream().
                filter(payment -> PaymentStatus.CANCELLED != payment.getStatus())
                .map(PaymentEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Payment createPayment(Payment payment) {
        if (paymentRepository.findByPaymentId(payment.getPaymentId()).isPresent())
            throw new OperationException(MessageCode.PAYMENT_ALREADY_EXISTS.name(),
                    "Уже существует платеж с идентификатором: " + payment.getPaymentId());

        var entity = mapper.mapToEntityFromModel(payment);
        entity.setStatus(PaymentStatus.NEW);
        entity = paymentRepository.save(entity);
        return mapper.mapToModelFromEntity(entity);
    }

    @Override
    public Payment acceptPayment(String paymentId) {
        return changePaymentStatus(paymentId, PaymentStatus.ACCEPTED);
    }

    @Override
    public Payment cancelPayment(String paymentId) {
        return changePaymentStatus(paymentId, PaymentStatus.CANCELLED);
    }

    @Override
    public void clearPayments() {
        // Переделать на запрос к базе
        paymentRepository.deleteAll();
    }

    private Payment changePaymentStatus(String paymentId, PaymentStatus status) {
        var entity = paymentRepository.findByPaymentId(paymentId)
                .orElseThrow(() -> new EntityNotExistsException(MessageCode.PAYMENT_NOT_EXISTS.name(),
                        "Не найден платеж с идентификатором: " + paymentId));

        if (entity.getStatus() != PaymentStatus.NEW)
            throw new OperationException(MessageCode.PAYMENT_TERMINATION_STATUS.name(),
                    "Не допустимо изменение статуса платежа с идентификатором: " + paymentId);

        entity.setStatus(status);
        entity = paymentRepository.save(entity);
        return mapper.mapToModelFromEntity(entity);
    }
}
