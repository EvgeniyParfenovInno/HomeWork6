package ru.demo.product.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.demo.product.dto.ProductDto;
import ru.demo.product.dto.ProductsDto;
import ru.demo.product.exception.ItemNotExistsException;
import ru.demo.product.mapping.ProductMapper;
import ru.demo.product.repository.ProductRepository;
import ru.demo.product.service.ProductService;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public ProductDto getById(Long id, Long userId) {
        validateProductId(id);
        validateUserId(userId);
        return repository.findByIdAndUserId(id, userId)
                .map(mapper::mapToDto)
                .orElseThrow(() -> new ItemNotExistsException("Не найден продукт с идентификатором " + id));
    }

    @Override
    public ProductsDto getByUserId(Long userId) {
        validateUserId(userId);
        return new ProductsDto().setItems(
                repository.findAllByUserId(userId).stream()
                        .map(mapper::mapToDto)
                        .toList());
    }

    private static void validateProductId(Long id) {
        if (id == null || id < 0)
            throw new IllegalArgumentException("Получен некорректный идентификатор продукта: " + id);
    }

    private static void validateUserId(Long id) {
        if (id == null || id < 0)
            throw new IllegalArgumentException("Получен некорректный идентификатор пользователя: " + id);
    }
}
