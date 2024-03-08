package ru.demo.product.service;

import ru.demo.product.dto.ProductDto;
import ru.demo.product.dto.ProductsDto;

public interface ProductService {
    ProductDto getById(Long id);
    ProductsDto getByUserId(Long userId);
}
