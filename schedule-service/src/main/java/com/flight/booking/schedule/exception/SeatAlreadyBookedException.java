package com.flight.booking.schedule.exception;

public class SeatAlreadyBookedException extends RuntimeException {
    public SeatAlreadyBookedException(String message) {
        super(message);
    }
}
