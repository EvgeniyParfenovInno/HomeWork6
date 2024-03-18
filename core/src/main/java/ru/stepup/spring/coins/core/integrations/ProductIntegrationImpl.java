package ru.stepup.spring.coins.core.integrations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import ru.stepup.spring.coins.core.exceptions.IntegrationException;
import ru.stepup.spring.coins.core.integrations.dtos.ProductDtoRs;
import ru.stepup.spring.coins.core.integrations.dtos.ProductsDtoRs;

import java.util.Collections;
import java.util.Optional;

@Slf4j
public class ProductIntegrationImpl implements ProductIntegration {

    private final RestTemplate restTemplate;

    public ProductIntegrationImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<ProductsDtoRs> getProductsByUserId(Long userId) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            httpHeaders.set("userId", userId.toString());
            var request = new HttpEntity(httpHeaders);
            var response = restTemplate.exchange("/api/v1/products", HttpMethod.GET,
                    request, ProductsDtoRs.class);
            return Optional.of(response).map(HttpEntity::getBody);
        } catch (IntegrationException e) {
            log.error("При обращении к продуктовому сервису обнаружена ошибка: {}", e.getIntegrationErrorDto());
            throw e;
        }
    }

    @Override
    public Optional<ProductDtoRs> getProductById(Long id, Long userId) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            httpHeaders.set("userId", userId.toString());
            var request = new HttpEntity(httpHeaders);
            var response = restTemplate.exchange("/api/v1/products/{productId}",
                    HttpMethod.GET, request, ProductDtoRs.class, id);
            return Optional.of(response).map(HttpEntity::getBody);
        } catch (IntegrationException e) {
            log.error("При обращении к продуктовому сервису обнаружена ошибка: {}", e.getIntegrationErrorDto());
            throw e;
        }
    }
}
