package ru.demo.limit.service.impl;

import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
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

    private final PaymentRepository repository;
    private final PaymentMapper mapper = Mappers.getMapper(PaymentMapper.class);

    @Override
    public Optional<Payment> getPayment(String paymentId) {
        var entity = repository.findByPaymentId(paymentId).orElse(null);
        return Optional.ofNullable(mapper.mapToModelFromEntity(entity));
    }

    @Override
    public BigDecimal getPaymentsSumByUserId(Long userId) {
        return repository.findAllByUserId(userId).stream().
                filter(payment -> PaymentStatus.CANCELLED != payment.getStatus())
                .map(PaymentEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Payment createPayment(Payment payment) {
        if (repository.findByPaymentId(payment.getPaymentId()).isPresent())
            throw new OperationException("101", "Уже существует платеж с идентификатором: " + payment.getPaymentId());

        var entity = mapper.mapToEntityFromModel(payment);
        entity.setStatus(PaymentStatus.NEW);
        entity = repository.save(entity);
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
        repository.deleteAll();
    }

    private Payment changePaymentStatus(String paymentId, PaymentStatus status) {
        var entity = repository.findByPaymentId(paymentId)
                .orElseThrow(() -> new EntityNotExistsException("102", "Не найден платеж с идентификатором: " + paymentId));

        if (entity.getStatus() != PaymentStatus.NEW)
            throw new OperationException("103", "Не допустимо изменение статуса платежа с идентификатором: " + paymentId);

        entity.setStatus(status);
        entity = repository.save(entity);
        return mapper.mapToModelFromEntity(entity);
    }
}
