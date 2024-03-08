package ru.demo.product.exception;

public class DatabaseCommonException extends RuntimeException {
    public DatabaseCommonException(String message) {
        super(message);
    }
}
