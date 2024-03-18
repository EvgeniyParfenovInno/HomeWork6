package ru.stepup.spring.coins.core.api;

import java.math.BigDecimal;

public record ExecuteCoinsRequest(
        String number,
        Long productId,
        String productType,
        BigDecimal amount
) {
}
