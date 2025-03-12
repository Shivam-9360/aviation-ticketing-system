package com.fligh.booking.schedule.mapper;

import com.fligh.booking.schedule.feign.AirportServiceCommunicator;
import com.fligh.booking.schedule.feign.FlightServiceCommunicator;
import com.fligh.booking.schedule.dto.*;
import com.fligh.booking.schedule.model.Schedule;
import com.fligh.booking.schedule.exception.CommunicationFailedException;
import com.fligh.booking.schedule.model.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduleMapper {
    private final FlightServiceCommunicator flightServiceCommunicator;
    private final AirportServiceCommunicator airportServiceCommunicator;

    public ScheduleResponse mapToDTO(Schedule schedule){
        DTO<FlightResponse> flightResponse = flightServiceCommunicator.getFlightById(schedule.getFlightId());
        if(flightResponse == null){
            throw new CommunicationFailedException("Flights not found");
        }
        DTO<AirportResponse> sourceAirport =  airportServiceCommunicator.getAirportById(schedule.getSourceAirportId());
        DTO<AirportResponse> destinationAirport =  airportServiceCommunicator.getAirportById(schedule.getDestinationAirportId());

        if(!sourceAirport.isSuccess() || !destinationAirport.isSuccess()){
            throw new CommunicationFailedException("Airports Not Found");
        }

        return ScheduleResponse
                .builder()
                .flightResponse(flightResponse.getData())
                .sourceAirportResponse(sourceAirport.getData())
                .destinationAirportResponse(destinationAirport.getData())
                .dateTime(schedule.getDateTime())
                .seats(schedule.getSeats())
                .build();
    }

    public  Schedule mapToModel(ScheduleRequest scheduleRequest){
        DTO<FlightResponse> flightResponse = flightServiceCommunicator.getFlightById(scheduleRequest.getFlightId());
        if(!flightResponse.isSuccess()){
            throw new CommunicationFailedException("Flights not found");
        }

        DTO<AirportResponse> sourceAirport =  airportServiceCommunicator.getAirportById(scheduleRequest.getSourceAirport());
        DTO<AirportResponse> destinationAirport =  airportServiceCommunicator.getAirportById(scheduleRequest.getDestinationAirport());
        if(!sourceAirport.isSuccess() || !destinationAirport.isSuccess()){
            throw new CommunicationFailedException("Airports Not Found");
        }

        List<Seat> seats =  new ArrayList<>();
        for (int i = 0; i <flightResponse.getData().getTotalSeats(); i++) {
            seats.add(Seat.builder()
                            .seatNumber(i+1)
                            .isBooked(false)
                    .build());
        }

        return  Schedule.builder()
                .seats(seats)
                .flightId(scheduleRequest.getFlightId())
                .sourceAirportId(scheduleRequest.getSourceAirport())
                .destinationAirportId(scheduleRequest.getDestinationAirport())
                .dateTime(scheduleRequest.getDateTime())
                .build();
    }
}
