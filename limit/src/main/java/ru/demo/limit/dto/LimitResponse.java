package ru.demo.limit.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LimitResponse {
    private final BigDecimal limit;
}
