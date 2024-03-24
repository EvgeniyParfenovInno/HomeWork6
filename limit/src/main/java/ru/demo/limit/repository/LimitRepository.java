package ru.demo.limit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demo.limit.entity.Limit;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {
}
