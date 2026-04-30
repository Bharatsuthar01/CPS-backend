package com.bank.cps.common.exception;

import com.bank.cps.common.api.ApiError;
import com.bank.cps.common.api.ApiResponse;
import com.bank.cps.common.logging.CorrelationIdFilter;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ApiResponse<List<ApiError>> handleBusiness(BusinessException ex) {
        return ApiResponse.fail(ex.getMessage(), CorrelationIdFilter.current(), List.of(new ApiError(ex.getCode(), null, ex.getMessage())));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<List<ApiError>> handleValidation(MethodArgumentNotValidException ex) {
        List<ApiError> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> new ApiError("VALIDATION_ERROR", err.getField(), err.getDefaultMessage()))
                .toList();
        return ApiResponse.fail("Validation failed", CorrelationIdFilter.current(), errors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<List<ApiError>> handleConstraint(ConstraintViolationException ex) {
        return ApiResponse.fail("Constraint violation", CorrelationIdFilter.current(), List.of(new ApiError("VALIDATION_ERROR", null, ex.getMessage())));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<List<ApiError>> handleOther(Exception ex) {
        return ApiResponse.fail("Unexpected error", CorrelationIdFilter.current(), List.of(new ApiError("INTERNAL_ERROR", null, ex.getMessage())));
    }
}
