package com.bank.cps.common.api;

import java.time.Instant;

public record ApiResponse<T>(boolean success, String message, String correlationId, T data, Instant timestamp) {
    public static <T> ApiResponse<T> ok(String message, String correlationId, T data) {
        return new ApiResponse<>(true, message, correlationId, data, Instant.now());
    }
    public static <T> ApiResponse<T> fail(String message, String correlationId, T data) {
        return new ApiResponse<>(false, message, correlationId, data, Instant.now());
    }
}
