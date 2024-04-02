package ru.demo.limit.exception;

public class EntityNotExistsException extends LimitApplicationException {
    public EntityNotExistsException(String code, String message) {
        super(code, message);
    }
}
