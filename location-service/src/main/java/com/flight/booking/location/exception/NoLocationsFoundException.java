package com.flight.booking.location.exception;

public class NoLocationsFoundException extends RuntimeException {
    public NoLocationsFoundException(String message) {
        super(message);
    }
}
