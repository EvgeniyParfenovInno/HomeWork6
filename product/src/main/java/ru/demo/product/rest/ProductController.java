package ru.demo.product.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.demo.product.dto.ProductDto;
import ru.demo.product.dto.ProductsDto;
import ru.demo.product.service.ProductService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "/{productId}")
    public ProductDto getProduct(@PathVariable Long productId) {
        log.info("Принят запрос на получение продукта с идентификатором: {}", productId);
        return productService.getById(productId);
    }

    @GetMapping
    public ProductsDto getProducts(@RequestParam Long userId) {
        log.info("Принят запрос на получение продуктов с идентификатором пользователя: {}", userId);
        return productService.getByUserId(userId);
    }
}
