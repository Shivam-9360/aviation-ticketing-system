package com.flight.booking.mapper;

import com.flight.booking.dto.BookingRequest;
import com.flight.booking.dto.BookingResponse;
import com.flight.booking.feign.ScheduleServiceCommunicator;
import com.flight.booking.model.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookingMapper {
    private final ScheduleServiceCommunicator scheduleServiceCommunicator;
    public Booking mapToModel(BookingRequest bookingRequest) {
        return Booking.builder()
                .userId(bookingRequest.getUserId())
                .scheduleId(bookingRequest.getScheduleId())
                .seatNumbers(bookingRequest.getSeatNumbers())
                .amount(bookingRequest.getAmount())
                .razorPayOrderId(bookingRequest.getRazorpayOrderId())
                .razorpayPaymentId(bookingRequest.getRazorpayPaymentId())
                .dateTime(java.time.Instant.now())
                .build();
    }

    public BookingResponse mapToDTO(Booking booking) {

        return BookingResponse.builder()
                .id(booking.getId())
                .userId(booking.getUserId())
                .scheduleId(booking.getScheduleId())
                .seatNumbers(booking.getSeatNumbers())
                .amount(booking.getAmount())
                .dateTime(booking.getDateTime())
                .razorpayPaymentId(booking.getRazorpayPaymentId())
                .build();
    }
}
