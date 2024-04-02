package ru.demo.limit.exception;

public class OperationException extends LimitApplicationException {
    public OperationException(String code, String message) {
        super(code, message);
    }
}