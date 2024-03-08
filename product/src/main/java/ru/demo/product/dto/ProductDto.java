package ru.demo.product.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class ProductDto {
    private Long id;
    private String account;
    private BigDecimal balance;
    private Integer type;
}
