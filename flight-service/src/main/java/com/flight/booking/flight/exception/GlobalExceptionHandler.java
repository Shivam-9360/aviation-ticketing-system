package com.flight.booking.flight.exception;

import com.flight.booking.flight.dto.DTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(FlightNotFoundException.class)
    public ResponseEntity<DTO<String>> handleNoUserException(FlightNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DTO.<String>builder()
                        .success(true)
                        .message(ex.getMessage())
                        .data(null)
                .build());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DTO<String>> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(DTO.<String>builder()
                .success(false)
                .message("Something Went Wrong: " + ex.getMessage())
                .data(null)
                .build());
    }
}
