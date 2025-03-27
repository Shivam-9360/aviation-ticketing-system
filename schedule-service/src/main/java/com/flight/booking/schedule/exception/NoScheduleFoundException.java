package com.flight.booking.schedule.exception;

public class NoScheduleFoundException extends RuntimeException {
    public NoScheduleFoundException(String message) {
        super(message);
    }
}
