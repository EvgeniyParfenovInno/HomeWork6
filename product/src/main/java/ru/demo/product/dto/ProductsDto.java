package ru.demo.product.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;

@Data
@Accessors(chain = true)
public class ProductsDto {
    private List<ProductDto> items = Collections.emptyList();
}
