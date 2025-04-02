package com.flight.booking.service;

import com.flight.booking.dto.BookingRequest;
import com.flight.booking.dto.BookingResponse;
import com.flight.booking.dto.PaymentRequest;
import com.flight.booking.dto.PaymentResponse;
import com.flight.booking.feign.PaymentServiceCommunicator;
import com.flight.booking.feign.ScheduleServiceCommunicator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final PaymentServiceCommunicator paymentServiceCommunicator;
    private final ScheduleServiceCommunicator scheduleServiceCommunicator;

    @Override
    public PaymentResponse getOrderId(BookingRequest bookingRequest) {
        try{
            boolean isValid = validateBookingRequest(bookingRequest);
            if (!isValid) {
                throw new RuntimeException("Invalid Booking Request");
            }
            return paymentServiceCommunicator.getOrderId(PaymentRequest.builder()
                            .amount(bookingRequest.getAmount())
                            .userId(bookingRequest.getUserId())
                            .currency("INR")
                    .build()).getData();
        } catch (Exception e) {
            throw new RuntimeException("Payment Service Unavailable");
        }
    }

    public boolean validateBookingRequest(BookingRequest bookingRequest) {
        try {
            return scheduleServiceCommunicator.validateBookingRequest(bookingRequest).isSuccess();
        } catch (Exception e) {
            return false;
        }
    }
}
