package com.flight.booking.gateway.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Component
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthResponse {
    public String status;
}
