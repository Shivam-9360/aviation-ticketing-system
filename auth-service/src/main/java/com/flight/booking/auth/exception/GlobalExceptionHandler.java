package com.flight.booking.auth.exception;

import com.flight.booking.auth.dto.DTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CredentialsNotValidException.class)
    public ResponseEntity<DTO<String>> handleNoUserException(CredentialsNotValidException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(DTO.<String>builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .build());
    }
}
