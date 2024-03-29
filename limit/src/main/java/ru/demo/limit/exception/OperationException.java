package ru.demo.limit.exception;

public class OperationException extends ExtendedException {
    public OperationException(String code, String message) {
        super(code, message);
    }
}