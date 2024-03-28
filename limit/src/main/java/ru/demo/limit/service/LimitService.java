package ru.demo.limit.service;

import ru.demo.limit.model.Limit;

public interface LimitService {
    Limit getLimitByUserId(Long userId);
}
