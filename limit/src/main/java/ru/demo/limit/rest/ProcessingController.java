package ru.demo.limit.rest;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.demo.limit.api.PaymentCreatureRequest;
import ru.demo.limit.api.PaymentResponse;
import ru.demo.limit.service.ProcessingService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/payment")
public class ProcessingController {

    private final ProcessingService service;

    @PostMapping
    public PaymentResponse createPayment(@RequestHeader Long userId, @RequestBody PaymentCreatureRequest request) {
        log.info("Получен запрос на регистрацию платежа: {}", request);
        return service.createPayment(userId, request);
    }

    @PutMapping("/{paymentId}")
    public PaymentResponse acceptPayment(@RequestHeader Long userId, @PathVariable String paymentId) {
        log.info("Получен запрос на подтверждение платежа: {}", paymentId);
        return service.acceptPayment(userId, paymentId);
    }

    @DeleteMapping("/{paymentId}")
    public PaymentResponse cancelPayment(@RequestHeader Long userId, @PathVariable String paymentId) {
        log.info("Получен запрос на отмену платежа: {}", paymentId);
        return service.cancelPayment(userId, paymentId);
    }
}
