package com.flight.booking.airport.exception;

public class AirportDoesntExistException extends RuntimeException {
    public AirportDoesntExistException(String message) {
        super(message);
    }
}
