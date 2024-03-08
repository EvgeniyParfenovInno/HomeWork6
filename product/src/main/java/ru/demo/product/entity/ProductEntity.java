package ru.demo.product.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class ProductEntity {
    private Long id;
    private String account;
    private BigDecimal balance;
    private Integer type;
    private Long userId;
}
