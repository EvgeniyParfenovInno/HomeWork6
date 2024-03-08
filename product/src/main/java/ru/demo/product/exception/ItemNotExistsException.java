package ru.demo.product.exception;

public class ItemNotExistsException extends RuntimeException {
    public ItemNotExistsException(String message) {
        super(message);
    }
}
