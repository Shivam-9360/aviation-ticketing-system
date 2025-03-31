package com.flight.booking.service;

import com.flight.booking.dto.PaymentRequest;
import com.flight.booking.dto.PaymentResponse;
import com.flight.booking.dto.VerificationRequest;
import com.razorpay.RazorpayException;

public interface PaymentService {
    public PaymentResponse createOrder(PaymentRequest paymentRequest) throws RazorpayException;
    public boolean verifyPayment(VerificationRequest verificationRequest);
}
