package com.flight.booking.schedule.mapper;

import com.flight.booking.schedule.dto.*;
import com.flight.booking.schedule.enums.SeatStatus;
import com.flight.booking.schedule.feign.AirportServiceCommunicator;
import com.flight.booking.schedule.feign.FlightServiceCommunicator;
import com.flight.booking.schedule.dto.*;
import com.flight.booking.schedule.model.Schedule;
import com.flight.booking.schedule.exception.CommunicationFailedException;
import com.flight.booking.schedule.model.Seat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduleMapper {
    private final FlightServiceCommunicator flightServiceCommunicator;
    private final AirportServiceCommunicator airportServiceCommunicator;

    public ScheduleResponse mapToDTO(Schedule schedule, boolean isById){
        DTO<FlightResponse> flightResponse = flightServiceCommunicator.getFlightById(schedule.getFlightId());
        if(flightResponse == null){
            throw new CommunicationFailedException("Flights not found");
        }
        DTO<AirportResponse> sourceAirport =  airportServiceCommunicator.getAirportById(schedule.getSourceAirportId());
        DTO<AirportResponse> destinationAirport =  airportServiceCommunicator.getAirportById(schedule.getDestinationAirportId());

        if(!sourceAirport.isSuccess() || !destinationAirport.isSuccess()){
            throw new CommunicationFailedException("Airports Not Found");
        }
        if(isById){
            return ScheduleResponse
                    .builder()
                    .id(schedule.getId())
                    .flightResponse(flightResponse.getData())
                    .sourceAirportResponse(sourceAirport.getData())
                    .destinationAirportResponse(destinationAirport.getData())
                    .dateTime(schedule.getDateTime().toString())
                    .seats(schedule.getSeats())
                    .build();
        }
       return ScheduleResponse
                .builder()
                .id(schedule.getId())
                .flightResponse(flightResponse.getData())
                .sourceAirportResponse(sourceAirport.getData())
                .destinationAirportResponse(destinationAirport.getData())
               .dateTime(schedule.getDateTime().toString())
                .seats(null)
                .build();

    }

    public Schedule mapToModel(ScheduleRequest scheduleRequest) {

        try {
            DTO<FlightResponse> flightResponse = flightServiceCommunicator.getFlightById(scheduleRequest.getFlightId());
            if (!flightResponse.isSuccess()) {
                throw new CommunicationFailedException("Flights not found");
            }

            DTO<AirportResponse> sourceAirport = airportServiceCommunicator.getAirportById(scheduleRequest.getSourceAirport());
            DTO<AirportResponse> destinationAirport = airportServiceCommunicator.getAirportById(scheduleRequest.getDestinationAirport());
            if (!sourceAirport.isSuccess() || !destinationAirport.isSuccess()) {
                throw new CommunicationFailedException("Airports Not Found");
            }

            List<Seat> seats = new ArrayList<>();
            for (int i = 0; i < flightResponse.getData().getTotalSeats(); i++) {
                seats.add(Seat.builder()
                        .seatNumber(i + 1)
                        .status(SeatStatus.VACANT)
                        .build());
            }

            return Schedule.builder()
                    .seats(seats)
                    .flightId(scheduleRequest.getFlightId())
                    .sourceAirportId(scheduleRequest.getSourceAirport())
                    .destinationAirportId(scheduleRequest.getDestinationAirport())
                    .dateTime(scheduleRequest.getDateTime())
                    .build();
        }catch (RuntimeException ex){
            throw new CommunicationFailedException("Couldn't Create Schedule, Communication failed");
        }
    }

}
