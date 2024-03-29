package ru.demo.limit.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.demo.limit.dto.ApiErrorResponse;
import ru.demo.limit.exception.EntityNotExistsException;
import ru.demo.limit.exception.OperationException;
import ru.demo.limit.exception.ValidationException;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    private static final String ERROR_MESSAGE = "Обнаружена ошибка: {}";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ApiErrorResponse handleValidationException(ValidationException e) {
        log.error(ERROR_MESSAGE, e.getMessage());
        return new ApiErrorResponse(e.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OperationException.class)
    public ApiErrorResponse handleOperationException(OperationException e) {
        log.error(ERROR_MESSAGE, e.getMessage());
        return new ApiErrorResponse(e.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotExistsException.class)
    public ApiErrorResponse handleEntityNotExistsException(EntityNotExistsException e) {
        log.error(ERROR_MESSAGE, e.getMessage());
        return new ApiErrorResponse(e.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ApiErrorResponse handleThrowable(Throwable t) {
        log.error(ERROR_MESSAGE, t.getMessage());
        return new ApiErrorResponse("900", t.getMessage());
    }
}
