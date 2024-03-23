package ru.stepup.spring.coins.core.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stepup.spring.coins.core.api.ProductsResponse;
import ru.stepup.spring.coins.core.services.ProductService;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ProductsResponse getProducts(@RequestHeader Long userId) {
        log.info("Запрос продуктов пользователя: {}", userId);
        return productService.getProductByUserId(userId);
    }
}
