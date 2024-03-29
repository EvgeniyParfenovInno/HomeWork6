package ru.demo.limit.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.demo.limit.dto.PaymentCreatureRequest;
import ru.demo.limit.dto.PaymentResponse;
import ru.demo.limit.entity.PaymentEntity;
import ru.demo.limit.model.Payment;

import java.math.BigDecimal;

@Mapper
public interface PaymentMapper {
    @Mapping(target = "paymentStatus", expression = "java(payment.getStatus().name())")
    PaymentResponse mapToDtoFromModel(Payment payment, BigDecimal currentLimit);
    Payment mapToModelFromEntity(PaymentEntity entity);
    Payment mapToModelFromDto(PaymentCreatureRequest request, Long userId);
    PaymentEntity mapToEntityFromModel(Payment model);
}
