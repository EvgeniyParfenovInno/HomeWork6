package ru.demo.limit.exception;

public class EntityIllegalStateException extends ExtendedException {
    public EntityIllegalStateException(String code, String message) {
        super(code, message);
    }
}
