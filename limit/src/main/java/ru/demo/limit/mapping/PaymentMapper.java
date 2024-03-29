package ru.demo.limit.mapping;

import org.mapstruct.Mapper;
import ru.demo.limit.api.PaymentCreatureRequest;
import ru.demo.limit.api.PaymentResponse;
import ru.demo.limit.entity.PaymentEntity;
import ru.demo.limit.model.Payment;

import java.math.BigDecimal;

@Mapper
public interface PaymentMapper {
    Payment mapToModelFromEntity(PaymentEntity entity);
    Payment mapToModelFromDto(PaymentCreatureRequest request, Long userId);
    PaymentResponse mapToDtoFromModel(Payment payment, BigDecimal currentLimit);
    PaymentEntity mapToEntityFromModel(Payment model);
}
