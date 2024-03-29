package ru.demo.limit.exception;

public class EntityNotExistsException extends ExtendedException {
    public EntityNotExistsException(String code, String message) {
        super(code, message);
    }
}
