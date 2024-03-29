package ru.demo.limit.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.demo.limit.api.LimitResponse;
import ru.demo.limit.mapping.LimitMapper;
import ru.demo.limit.service.LimitService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/limit")
public class LimitController {

    private final LimitService limitService;
    private final LimitMapper mapper = Mappers.getMapper(LimitMapper.class);

    @GetMapping
    public LimitResponse getLimit(@RequestHeader Long userId){
        log.info("Получен запрос текущего лимита пользователя: {}", userId);
        return mapper.mapToDto(limitService.getLimitByUserId(userId));
    }
}
