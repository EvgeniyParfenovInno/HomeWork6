package ru.stepup.spring.coins.core.integrations;

import ru.stepup.spring.coins.core.integrations.dtos.ProductDtoRs;
import ru.stepup.spring.coins.core.integrations.dtos.ProductsDtoRs;

import java.util.Optional;

public interface ProductIntegration {
    Optional<ProductsDtoRs> getProductsByUserId(Long userId);
    Optional<ProductDtoRs> getProductById(Long id, Long userId);
}
