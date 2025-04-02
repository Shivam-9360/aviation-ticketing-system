package com.flight.booking.service;

import com.flight.booking.dto.PaymentRequest;
import com.flight.booking.dto.PaymentResponse;
import com.flight.booking.dto.VerificationRequest;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private RazorpayClient razorpayClient;

    @Value("${razorpay.api.secret}")
    private String RAZORPAY_API_SECRET;

    @Override
    public PaymentResponse createOrder(PaymentRequest paymentRequest) throws RazorpayException {
        JSONObject options = new JSONObject();
        options.put("amount", paymentRequest.getAmount() * 100);
        options.put("currency", paymentRequest.getCurrency());

        Order razorPayOrder = razorpayClient.orders.create(options);
        return PaymentResponse.builder()
                .userId(paymentRequest.getUserId())
                .amount(paymentRequest.getAmount())
                .currency(paymentRequest.getCurrency())
                .razorpayOrderId(razorPayOrder.get("id").toString())
                .build();
    }

    @Override
    public boolean verifyPayment(VerificationRequest verificationRequest) {
        String payload = verificationRequest.getRazorpay_order_id() + '|' + verificationRequest.getRazorpay_payment_id();
        try {
            return Utils.verifySignature(payload, verificationRequest.getRazorpay_signature(), RAZORPAY_API_SECRET);
        } catch (Exception e) {
            return false;
        }
    }
}
