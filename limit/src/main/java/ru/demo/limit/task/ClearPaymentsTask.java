package ru.demo.limit.task;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Component;
import ru.demo.limit.service.PaymentService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "service.clear-payments")
public class ClearPaymentsTask {

    @Setter
    private CronExpression cron;

    private final PaymentService paymentService;

    @PostConstruct
    private void init() {
        log.info("Удаление платежей, расписание: cron={}", cron);
        log.info("Первый запуск удаления платежей: {}", getNextRun());
    }

    @Scheduled(cron = "${service.clear-payments.cron}")
    public void execute() {
        log.info("Запущен процесс удаления платежей...");
        paymentService.clearPayments();
        log.info("Удаление платежей выполнено. Следующий запуск: {}", getNextRun());
    }

    private String getNextRun() {
        return Optional.ofNullable(cron.next(LocalDateTime.now()))
                .map(next -> next.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .orElse("Запуск задач не запланирован");
    }
}
