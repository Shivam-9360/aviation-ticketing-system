package com.flight.booking.service;

import com.flight.booking.dto.BookingRequest;
import com.flight.booking.dto.PaymentRequest;
import com.flight.booking.dto.PaymentResponse;

public interface BookingService {
    public PaymentResponse getOrderId(BookingRequest bookingRequest);
}
