package ru.demo.limit.task;

import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class CronConverter implements Converter<String, CronExpression> {

    @Override
    public CronExpression convert(@NonNull String source) {
        return CronExpression.parse(source);
    }
}