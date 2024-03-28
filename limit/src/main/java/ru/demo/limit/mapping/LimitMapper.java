package ru.demo.limit.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.demo.limit.api.LimitResponse;
import ru.demo.limit.entity.LimitEntity;
import ru.demo.limit.model.Limit;

@Mapper
public interface LimitMapper {
    Limit mapToModel(LimitEntity entity);

    @Mapping(target = "limit", source = "value")
    LimitResponse mapToDto(Limit limit);
}
