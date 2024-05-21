package com.example.shoppingmall.exception;

import com.example.shoppingmall.utils.ApiUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.example.shoppingmall.utils.ApiUtils.error;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler // (MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiUtils.ApiResult<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException errors){
        Map<String, String> errorMessages = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String errorField = error.getField();
            String errorMessage = error.getDefaultMessage();
            errorMessages.put(errorField, errorMessage);
        }

        return error(errorMessages, HttpStatus.BAD_REQUEST);

    }
}
