package ru.demo.limit.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Limit {
    private final BigDecimal value;
}
