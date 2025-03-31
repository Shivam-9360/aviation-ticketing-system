package com.flight.booking.controller;

import com.flight.booking.dto.DTO;
import com.flight.booking.dto.PaymentRequest;
import com.flight.booking.dto.PaymentResponse;
import com.flight.booking.dto.VerificationRequest;
import com.flight.booking.service.PaymentService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/order")
    public ResponseEntity<DTO<PaymentResponse>> createOrder(@RequestBody PaymentRequest paymentRequest) throws RazorpayException {
        PaymentResponse paymentResponse = paymentService.createOrder(paymentRequest);
        return ResponseEntity.ok(DTO.<PaymentResponse>builder()
                .success(true)
                .message("Order Created")
                .data(paymentResponse)
                .build());
    }

    @PostMapping("/verify")
    public ResponseEntity<DTO<Boolean>> verifyPayment(@RequestBody VerificationRequest verificationRequest) {
        boolean status = paymentService.verifyPayment(verificationRequest);
        return ResponseEntity.ok(DTO.<Boolean>builder()
                .success(status)
                .message(status ? "Payment Verified" : "Payment Verification Failed")
                .build());
    }
}
