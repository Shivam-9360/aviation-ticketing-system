package com.flight.booking.airport.exception;

public class AirportAlreadyExistsException extends RuntimeException {
    public AirportAlreadyExistsException(String message) {
        super(message);
    }
}
