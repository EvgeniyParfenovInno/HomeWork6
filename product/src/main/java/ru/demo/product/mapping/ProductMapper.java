package ru.demo.product.mapping;

import org.mapstruct.Mapper;
import ru.demo.product.dto.ProductDto;
import ru.demo.product.entity.ProductEntity;

@Mapper
public interface ProductMapper {
    ProductDto mapToDto(ProductEntity entity);
}
