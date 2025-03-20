package com.flight.booking.airport.exception;

public class CommunicationFailedException extends RuntimeException {
    public CommunicationFailedException(String message) {
        super(message);
    }
}
