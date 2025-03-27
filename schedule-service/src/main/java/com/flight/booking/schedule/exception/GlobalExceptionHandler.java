package com.flight.booking.schedule.exception;


import com.flight.booking.schedule.dto.DTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CommunicationFailedException.class)
    public ResponseEntity<DTO<String>> handleNoUserException(CommunicationFailedException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DTO.<String>builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .build());
    }

    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<DTO<String>> handleInvalidCredentials(InvalidCredentials ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DTO.<String>builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .build());
    }
    @ExceptionHandler(NoScheduleFoundException.class)
    public ResponseEntity<DTO<String>> handleNoScheduleFound(NoScheduleFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DTO.<String>builder()
                .success(false)
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
