package ru.stepup.spring.coins.core.integrations;

import ru.stepup.spring.coins.core.integrations.dtos.ProductsDtoRs;

public interface ProductIntegration {
    ProductsDtoRs getProductsByUserId(String userId);
}
