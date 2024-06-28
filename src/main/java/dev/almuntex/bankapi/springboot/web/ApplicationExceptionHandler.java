package dev.almuntex.bankapi.springboot.web;

import dev.almuntex.bankapi.springboot.dto.ValidationErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationErrorDto handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> invalidFields = ex.getFieldErrors().stream().map(FieldError::getField).toList();
        return new ValidationErrorDto(ex.getMessage(), invalidFields);
    }
}
