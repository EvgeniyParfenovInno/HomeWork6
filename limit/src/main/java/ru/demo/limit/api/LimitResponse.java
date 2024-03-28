package ru.demo.limit.api;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LimitResponse {
    private final BigDecimal limit;
}
