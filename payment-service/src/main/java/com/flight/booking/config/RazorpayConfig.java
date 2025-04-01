package com.flight.booking.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfig {
    @Value("${razorpay.api.key}")
    private String RAZORPAY_API_KEY;

    @Value("${razorpay.api.secret}")
    private String RAZORPAY_API_SECRET;

    @Bean
    public RazorpayClient razorpayClient() throws RazorpayException {
        return new RazorpayClient(RAZORPAY_API_KEY, RAZORPAY_API_SECRET);
    }
}