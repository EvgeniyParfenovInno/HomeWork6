package ru.demo.limit.exception;

public class ValidationException extends LimitApplicationException {
    public ValidationException(String code, String message) {
        super(code, message);
    }
}