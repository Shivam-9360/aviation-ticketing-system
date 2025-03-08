package com.fligh.booking.schedule.mapper;

import com.fligh.booking.schedule.feign.AirportServiceCommunicator;
import com.fligh.booking.schedule.feign.FlightServiceCommunicator;
import com.fligh.booking.schedule.dto.*;
import com.fligh.booking.schedule.entity.Schedule;
import com.fligh.booking.schedule.exception.CommunicationFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleMapper {

    @Autowired
    private FlightServiceCommunicator flightServiceCommunicator;

    @Autowired
    private AirportServiceCommunicator airportServiceCommunicator;

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
                .build();
    }

    public  Schedule mapToModel(ScheduleRequest scheduleRequest){
        DTO<FlightResponse> flightResponse = flightServiceCommunicator.getFlightById(scheduleRequest.getFlightId());
        if(flightResponse == null){
            throw new CommunicationFailedException("Flights not found");
        }
        DTO<AirportResponse> sourceAirport =  airportServiceCommunicator.getAirportById(scheduleRequest.getSourceAirport());
        DTO<AirportResponse> destinationAirport =  airportServiceCommunicator.getAirportById(scheduleRequest.getDestinationAirport());

        if(!sourceAirport.isSuccess() || !destinationAirport.isSuccess()){
            throw new CommunicationFailedException("Airports Not Found");
        }
        return  Schedule.builder()
                .flightId(scheduleRequest.getFlightId())
                .sourceAirportId(scheduleRequest.getSourceAirport())
                .destinationAirportId(scheduleRequest.getDestinationAirport())
                .dateTime(scheduleRequest.getDateTime())
                .build();
    }
}
