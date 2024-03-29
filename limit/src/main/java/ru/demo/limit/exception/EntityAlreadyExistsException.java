package ru.demo.limit.exception;

public class EntityAlreadyExistsException extends ExtendedException {
    public EntityAlreadyExistsException(String code, String message) {
        super(code, message);
    }
}