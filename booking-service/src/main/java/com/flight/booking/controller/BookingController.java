package com.flight.booking.controller;

import com.flight.booking.dto.DTO;
import com.flight.booking.dto.PaymentRequest;
import com.flight.booking.dto.PaymentResponse;
import com.flight.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/create-order")
    public ResponseEntity<DTO<PaymentResponse>> getPaymentOrderId(@RequestBody PaymentRequest paymentRequest) {
        PaymentResponse orderIdObject =  bookingService.getOrderId(paymentRequest);
        return ResponseEntity.ok(DTO.<PaymentResponse>builder()
                .success(true)
                .message("Booking Created")
                .data(orderIdObject)
                .build());
    }
}
