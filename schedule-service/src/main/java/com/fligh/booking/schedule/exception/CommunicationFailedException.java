package com.fligh.booking.schedule.exception;

public class CommunicationFailedException extends RuntimeException {
    public CommunicationFailedException(String message) {
        super(message);
    }
}
