package ru.stepup.spring.coins.core.api;

import java.math.BigDecimal;

public record ProductResponse(Long id, String account, BigDecimal balance, Integer type) {
}
