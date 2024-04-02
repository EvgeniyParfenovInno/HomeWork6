package ru.demo.limit.service.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.demo.limit.entity.LimitEntity;
import ru.demo.limit.mapping.LimitMapper;
import ru.demo.limit.model.Limit;
import ru.demo.limit.repository.LimitRepository;
import ru.demo.limit.service.LimitService;

import java.math.BigDecimal;

@Service
public class LimitServiceImpl implements LimitService {

    @Value("${service.limit}")
    private BigDecimal defaultLimit;

    private final LimitRepository limitRepository;
    private final LimitMapper mapper = Mappers.getMapper(LimitMapper.class);

    public LimitServiceImpl(LimitRepository limitRepository) {
        this.limitRepository = limitRepository;
    }

    @Override
    public Limit getLimitByUserId(Long userId) {
        return mapper.mapToModel(limitRepository.findByUserId(userId)
                .orElseGet(() -> createLimit(userId)));
    }

    private LimitEntity createLimit(Long userId) {
        return limitRepository.save(new LimitEntity()
                .setUserId(userId)
                .setValue(defaultLimit));
    }
}
