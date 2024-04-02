package ru.demo.limit.exception;

import lombok.Getter;

public class LimitApplicationException extends RuntimeException {

    @Getter
    private final String code;

    public LimitApplicationException(String code, String message) {
        super(message);
        this.code = code;
    }
}
