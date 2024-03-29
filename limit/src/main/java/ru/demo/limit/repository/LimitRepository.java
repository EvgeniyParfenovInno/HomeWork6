package ru.demo.limit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demo.limit.entity.LimitEntity;

import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<LimitEntity, Long> {
    Optional<LimitEntity> findByUserId(Long userId);
}
