package com.flight.booking.service;

import com.flight.booking.dto.BookingRequest;
import com.flight.booking.dto.BookingResponse;
import com.flight.booking.dto.PaymentRequest;
import com.flight.booking.dto.PaymentResponse;

import java.util.List;

public interface BookingService {
    public PaymentResponse getOrderId(BookingRequest bookingRequest);
    public BookingResponse createBooking(BookingRequest bookingRequest);
    public List<BookingResponse> getAllBookings();
    public List<BookingResponse> getBookingByUserId(int userId);
}
