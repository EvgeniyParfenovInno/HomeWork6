package ru.demo.limit.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.demo.limit.repository.LimitRepository;
import ru.demo.limit.repository.PaymentRepository;

@Slf4j
@Service
@AllArgsConstructor
public class LimitServiceImpl {

    private final LimitRepository limitRepository;
    private final PaymentRepository paymentRepository;

}
