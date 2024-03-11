package ru.stepup.spring.coins.core.integrations.dtos;

import java.math.BigDecimal;

public record ProductDtoRs(Long id, String account, BigDecimal balance, Integer type) {
}
