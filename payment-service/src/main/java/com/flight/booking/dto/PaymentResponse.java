package com.flight.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse {
    private String userId;
    private double amount;
    private String currency;
    private String status;
    private String razorpayOrderId;
}
