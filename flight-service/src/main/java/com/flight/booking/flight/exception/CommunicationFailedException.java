package com.flight.booking.flight.exception;

public class CommunicationFailedException extends RuntimeException {
    public CommunicationFailedException(String message) {
        super(message);
    }
}
