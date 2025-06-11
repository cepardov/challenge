package com.globallogic.products.shared.exception;

import com.globallogic.products.shared.dto.ErrorResponse;
import com.globallogic.products.shared.dto.InputError;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        String errorCode = e.getErrorCode();
        String message = e.getMessage()
                != null ? e.getMessage() : "An error occurred";

        ErrorResponse errorResponse = new ErrorResponse(errorCode, message);
        log.error("BusinessException: {} - {}", errorCode, message);
        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        String errorCode = "APPLICATION_ERROR";
        String message = "An unexpected error occurred";

        ErrorResponse errorResponse = new ErrorResponse(errorCode, message);
        log.error("Exception: {} - {}", errorCode, message, e);
        return ResponseEntity.status(500).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidations(MethodArgumentNotValidException e) {
        String errorCode = "VALIDATION_ERROR";
        String message = "An error was found in the input data";

        List<InputError> errors = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new InputError(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        ErrorResponse errorResponse = new ErrorResponse(errorCode, message, errors);
        log.error("Validation exception: {}", errorResponse);
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
