package ru.stepup.spring.coins.core.services;

import org.springframework.stereotype.Service;
import ru.stepup.spring.coins.core.api.ExecuteCoinsRequest;
import ru.stepup.spring.coins.core.api.ExecuteCoinsResponse;
import ru.stepup.spring.coins.core.api.ProductResponse;
import ru.stepup.spring.coins.core.configurations.properties.CoreProperties;
import ru.stepup.spring.coins.core.exceptions.BadRequestException;

import java.math.BigDecimal;

@Service
public class CoinsService {
    private final CoreProperties coreProperties;
    private final ExecutorService executorService;
    private final ProductService productService;

    public CoinsService(CoreProperties coreProperties, ExecutorService executorService, ProductService productService) {
        this.coreProperties = coreProperties;
        this.executorService = executorService;
        this.productService = productService;
    }

    public ExecuteCoinsResponse execute(ExecuteCoinsRequest request, Long userId) {
        if (coreProperties.getNumbersBlockingEnabled()) {
            if (coreProperties.getBlockedNumbers().contains(request.number())) {
                throw new BadRequestException("Указан заблокированный номер кошелька", "BLOCKED_ACCOUNT_NUMBER");
            }
        }

        var product = productService.getProductById(request.productId(), userId);

        if (product.isPresent()) {
            if (product.map(ProductResponse::balance).orElse(BigDecimal.ZERO).compareTo(request.amount()) <= 0)
                throw new BadRequestException("Некорректный баланс", "INCORRECT_BALANCE");
        } else {
            throw new BadRequestException("Продукт не найден", "NOT_EXISTS_PRODUCT");
        }

        ExecuteCoinsResponse response = executorService.execute(request);
        return response;
    }
}
