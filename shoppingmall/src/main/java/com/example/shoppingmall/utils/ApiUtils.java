package com.example.shoppingmall.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ApiUtils {
    public static <T> ApiResult<T> success(T data) {
        return new ApiResult(true, data, null);
    }

    public static <M> ApiResult<M> error(M message, HttpStatus httpStatus) {
        return new ApiResult(false,
            null,
            new ApiError(message, httpStatus));
    }

    @AllArgsConstructor
    @Getter
    public static class ApiResult<T> {
        boolean success;
        T response;
        ApiError error;
    }

    @Getter
    @AllArgsConstructor
    static class ApiError<M> {
        M message;
        HttpStatus httpStatus;
    }
}
