package com.flight.booking.gateway.controller;

import com.flight.booking.gateway.dto.HealthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class Health {
    @RequestMapping("/**")
    public ResponseEntity<HealthResponse> schedule() {
        return ResponseEntity.status(HttpStatus.OK).body(
                HealthResponse.builder().status("DOWN").build());
    }
}
