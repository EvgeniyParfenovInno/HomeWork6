package ru.stepup.spring.coins.core.integrations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import ru.stepup.spring.coins.core.exceptions.IntegrationException;
import ru.stepup.spring.coins.core.integrations.dtos.ProductsDtoRs;

@Slf4j
public class ProductIntegrationImpl implements ProductIntegration {

    private final RestTemplate restTemplate;

    public ProductIntegrationImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ProductsDtoRs getProductsByUserId(String userId) {
        try {
            var response = restTemplate.getForEntity("/api/v1/products?userId={userId}",
                    ProductsDtoRs.class, userId);
            return response.getBody();
        } catch (IntegrationException e) {
            log.error("error body: {}", e.getIntegrationErrorDto());
            return null;
        }
    }
}
