package ru.demo.product.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.demo.product.dto.ApiErrorResponse;
import ru.demo.product.exception.DatabaseCommonException;
import ru.demo.product.exception.ItemNotExistsException;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class RestExceptionHandler {
    private static final String ERROR_TITLE = "Обнаружена ошибка: {}";
    private static final String OUT_ERROR_CODE_ILLEGAL_ARGUMENT = "001";
    private static final String OUT_ERROR_CODE_NOT_EXISTS = "002";
    private static final String OUT_ERROR_CODE_DB_ERROR = "100";
    private static final String OUT_ERROR_CODE_GLOBAL = "500";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiErrorResponse handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(ERROR_TITLE, e.getMessage());
        return new ApiErrorResponse(OUT_ERROR_CODE_ILLEGAL_ARGUMENT, e.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ItemNotExistsException.class)
    public ApiErrorResponse handleItemNotExistsException(ItemNotExistsException e) {
        log.error(ERROR_TITLE, e.getMessage());
        return new ApiErrorResponse(OUT_ERROR_CODE_NOT_EXISTS, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DatabaseCommonException.class)
    public ApiErrorResponse handleDatabaseCommonException(DatabaseCommonException e) {
        log.error(ERROR_TITLE, e.getMessage());
        return new ApiErrorResponse(OUT_ERROR_CODE_DB_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ApiErrorResponse handleThrowable(Throwable t) {
        log.error(ERROR_TITLE, t.getMessage());
        return new ApiErrorResponse(OUT_ERROR_CODE_GLOBAL, t.getMessage());
    }
}
