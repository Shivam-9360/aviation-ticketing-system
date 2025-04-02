package com.flight.booking.controller;

import com.flight.booking.dto.*;
import com.flight.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/create-order")
    public ResponseEntity<DTO<PaymentResponse>> getPaymentOrderId(@RequestBody BookingRequest bookingRequest) {
        PaymentResponse orderIdObject =  bookingService.getOrderId(bookingRequest);
        return ResponseEntity.ok(DTO.<PaymentResponse>builder()
                .success(true)
                .message("Order Created")
                .data(orderIdObject)
                .build());
    }

    //Create Booking
    @PostMapping("/booking")
    public ResponseEntity<DTO<BookingResponse>> createBooking(@RequestBody BookingRequest bookingRequest) {
        BookingResponse createdBooking =  bookingService.createBooking(bookingRequest);
        return ResponseEntity.ok().body(DTO.<BookingResponse>builder()
                        .data(createdBooking)
                        .message("Booking Done")
                        .success(true)
                .build());
    }

    //Get All Bookings
    @GetMapping("/bookings")
    public ResponseEntity<DTO<List<BookingResponse>>> getAllBookings() {
        List<BookingResponse> allBookings =  bookingService.getAllBookings();
        return ResponseEntity.ok().body(DTO.<List<BookingResponse>>builder()
                        .data(allBookings)
                        .message("All Bookings Fetched")
                        .success(true)
                .build());
    }
}
