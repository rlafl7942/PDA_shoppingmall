package com.example.shoppingmall.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
class ApiResult<T> {
    boolean success;
    T response;
    ApiError error;
}

@Getter
@AllArgsConstructor
class ApiError {
    String message;
    HttpStatus httpStatus;
}