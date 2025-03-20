package com.flight.booking.airport.exception;

import com.flight.booking.airport.dto.DTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AirportDoesntExistException.class)
    public ResponseEntity<DTO<String>> handleNoUserException(AirportDoesntExistException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DTO.<String>builder()
                        .success(false)
                        .message(ex.getMessage())
                        .data(null)
                .build());
    }

    @ExceptionHandler(CommunicationFailedException.class)
    public ResponseEntity<DTO<String>> handleCommunicationFailedException(CommunicationFailedException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DTO.<String>builder()
                .success(true)
                .message(ex.getMessage())
                .data(null)
                .build());
    }

    @ExceptionHandler(AirportAlreadyExistsException.class)
    public ResponseEntity<DTO<String>> handleUserAlreadyExistsException(AirportAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(DTO.<String>builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DTO<String>> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(DTO.<String>builder()
                .success(false)
                .message("Something went wrong: " + ex.getMessage())
                .data(null)
                .build());
    }
}
