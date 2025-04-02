package com.flight.booking.feign;

import com.flight.booking.dto.BookingRequest;
import com.flight.booking.dto.DTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "schedule-service")
public interface ScheduleServiceCommunicator {
    @PostMapping("/api/validate-booking")
    DTO<String> validateBookingRequest(BookingRequest bookingRequest);
    @PostMapping("/api/book-seats")
    DTO<String> bookSeats(BookingRequest bookingRequest);
}
