package ru.demo.limit.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ApiErrorResponse {
    private String code;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private OffsetDateTime date = OffsetDateTime.now();

    public ApiErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
