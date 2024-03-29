package ru.demo.limit.exception;

import lombok.Getter;

public class ExtendedException extends RuntimeException {

    @Getter
    private final String code;

    public ExtendedException(String code, String message) {
        super(message);
        this.code = code;
    }
}
