package ru.stepup.spring.coins.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.stepup.spring.coins.core.dtos.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new ErrorDto("RESOURCE_NOT_FOUND", e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDto> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(new ErrorDto(e.getCode(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IntegrationException.class)
    public ResponseEntity<ErrorDto> handleIntegrationException(IntegrationException e) {
        return new ResponseEntity<>(new ErrorDto(e.getIntegrationErrorDto().code(), e.getIntegrationErrorDto().message()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDto> handleThrowable(Throwable t) {
        var message = String.format("%s: %s", t.getClass().getSimpleName(), t.getMessage());
        return new ResponseEntity<>(new ErrorDto("INTERNAL_SERVER_ERROR", message), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
