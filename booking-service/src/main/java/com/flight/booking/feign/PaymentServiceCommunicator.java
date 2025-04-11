package com.flight.booking.feign;

import com.flight.booking.dto.DTO;
import com.flight.booking.dto.PaymentRequest;
import com.flight.booking.dto.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "payment-service", url = "${payment.service.url:}")
public interface PaymentServiceCommunicator {
    @PostMapping("/api/order")
    public DTO<PaymentResponse> getOrderId(PaymentRequest paymentRequest);
}
