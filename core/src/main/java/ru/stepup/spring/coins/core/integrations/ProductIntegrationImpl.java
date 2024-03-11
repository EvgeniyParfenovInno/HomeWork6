package ru.stepup.spring.coins.core.integrations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import ru.stepup.spring.coins.core.exceptions.IntegrationException;
import ru.stepup.spring.coins.core.integrations.dtos.ProductDtoRs;
import ru.stepup.spring.coins.core.integrations.dtos.ProductsDtoRs;

import java.util.Optional;

@Slf4j
public class ProductIntegrationImpl implements ProductIntegration {

    private final RestTemplate restTemplate;

    public ProductIntegrationImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<ProductsDtoRs> getProductsByUserId(String userId) {
        try {
            var response = restTemplate.getForEntity("/api/v1/products?userId={userId}",
                    ProductsDtoRs.class, userId);
            return Optional.of(response).map(HttpEntity::getBody);
        } catch (IntegrationException e) {
            log.error("При обращении к продуктовому сервису обнаружена ошибка: {}", e.getIntegrationErrorDto());
            throw e;
        }
    }

    @Override
    public Optional<ProductDtoRs> getProductById(Long id) {
        try {
            var response = restTemplate.getForEntity("/api/v1/products/{productId}",
                    ProductDtoRs.class, id);
            return Optional.of(response).map(HttpEntity::getBody);
        } catch (IntegrationException e) {
            log.error("При обращении к продуктовому сервису обнаружена ошибка: {}", e.getIntegrationErrorDto());
            throw e;
        }
    }
}
