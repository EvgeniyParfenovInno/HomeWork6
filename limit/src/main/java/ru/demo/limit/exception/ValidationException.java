package ru.demo.limit.exception;

public class ValidationException extends ExtendedException {
    public ValidationException(String code, String message) {
        super(code, message);
    }
}