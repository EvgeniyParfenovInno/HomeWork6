package ru.demo.product.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@EnableAutoConfiguration
@Configuration
@ConfigurationProperties("spring.datasource.hikari")
public class DataSourceConfig extends HikariConfig {
    @Bean
    public DataSource hikariDataSource() {
        return new HikariDataSource(this);
    }
}
