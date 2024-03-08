package ru.demo.product.repository;

import ru.demo.product.entity.ProductEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductDao {
    Optional<ProductEntity> getById(Long id) throws SQLException;
    List<ProductEntity> getByUserId(Long userId) throws SQLException;
}
