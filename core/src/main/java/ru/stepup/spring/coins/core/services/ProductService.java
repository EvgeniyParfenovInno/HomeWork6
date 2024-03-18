package ru.stepup.spring.coins.core.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.stepup.spring.coins.core.api.ProductResponse;
import ru.stepup.spring.coins.core.api.ProductsResponse;
import ru.stepup.spring.coins.core.exceptions.ResourceNotFoundException;
import ru.stepup.spring.coins.core.integrations.ProductIntegration;
import ru.stepup.spring.coins.core.integrations.dtos.ProductsDtoRs;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private final ProductIntegration productIntegration;

    public ProductsResponse getProductByUserId(Long userId) {
        var productsRs = productIntegration.getProductsByUserId(userId);
        var response = new ProductsResponse(
                productsRs.map(ProductsDtoRs::items)
                        .orElse(List.of())
                        .stream()
                        .map(p -> new ProductResponse(p.id(), p.account(), p.balance(), p.type()))
                        .toList());
        log.info("Подготовлен ответ: {}", response);
        return response;
    }

    public Optional<ProductResponse> getProductById(Long productId, Long userId) {
        var response = productIntegration.getProductById(productId, userId)
                .map(p -> new ProductResponse(p.id(), p.account(), p.balance(), p.type()));
        log.info("Подготовлен ответ: {}", response);
        return response;
    }
}
