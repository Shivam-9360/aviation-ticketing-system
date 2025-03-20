package com.flight.booking.airport.mapper;

import com.flight.booking.airport.dto.AirportRequest;
import com.flight.booking.airport.dto.AirportResponse;
import com.flight.booking.airport.entity.Airport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AirportMapper {
    public AirportResponse mapToDTO(Airport airport){
        return AirportResponse.builder()
                .name(airport.getAirportName())
                .id(airport.getId())
                .city(airport.getCity())
                .country(airport.getCountry())
                .build();
    }
    public Airport mapToModel(AirportRequest airport){
        return Airport.builder()
                .airportName(airport.getAirportName())
                .id(airport.getId())
                .country(airport.getCountry())
                .city(airport.getCity())
                .build();
    }
}
