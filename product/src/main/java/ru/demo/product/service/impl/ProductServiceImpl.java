package ru.demo.product.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.demo.product.dto.ProductDto;
import ru.demo.product.dto.ProductsDto;
import ru.demo.product.entity.ProductEntity;
import ru.demo.product.exception.DatabaseCommonException;
import ru.demo.product.exception.ItemNotExistsException;
import ru.demo.product.repository.ProductDao;
import ru.demo.product.service.ProductService;

import java.sql.SQLException;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Override
    public ProductDto getById(Long id, Long userId) {
        if (id == null || id < 0)
            throw new IllegalArgumentException("Получен некорректный идентификатор продукта: " + id);
        if (userId == null || userId < 0)
            throw new IllegalArgumentException("Получен некорректный идентификатор пользователя: " + userId);

        try {
            return productDao.getByIdAndUserId(id, userId)
                    .map(this::mapToDto)
                    .orElseThrow(() -> new ItemNotExistsException("Не найден продукт с идентификатором " + id));
        } catch (SQLException e) {
            log.error("При выполнении запроса обнаружена ошибка: {}", e.getMessage());
            throw new DatabaseCommonException("Получение продукта с идентификатором " + id + " завершилось ошибкой");
        }
    }

    @Override
    public ProductsDto getByUserId(Long userId) {
        try {
            return new ProductsDto().setItems(
                    productDao.getByUserId(userId).stream()
                            .map(this::mapToDto)
                            .toList());
        } catch (SQLException e) {
            log.error("При выполнении запроса обнаружена ошибка: {}", e.getMessage());
            throw new DatabaseCommonException("Получение продуктов по идентификатору пользователя завершилось ошибкой");
        }
    }

    private ProductDto mapToDto(ProductEntity entity) {
        return new ProductDto()
                .setAccount(entity.getAccount())
                .setId(entity.getId())
                .setType(entity.getType())
                .setBalance(entity.getBalance());
    }
}
