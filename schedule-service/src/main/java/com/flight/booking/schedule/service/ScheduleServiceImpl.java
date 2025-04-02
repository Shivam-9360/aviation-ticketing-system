package com.flight.booking.schedule.service;

import com.flight.booking.schedule.dto.*;
import com.flight.booking.schedule.dto.ScheduleRequest;
import com.flight.booking.schedule.dto.ScheduleResponse;
import com.flight.booking.schedule.enums.SeatStatus;
import com.flight.booking.schedule.exception.SeatAlreadyBookedException;
import com.flight.booking.schedule.exception.SeatNotFoundException;
import com.flight.booking.schedule.feign.FlightServiceCommunicator;
import com.flight.booking.schedule.model.Schedule;
import com.flight.booking.schedule.exception.NoScheduleFoundException;
import com.flight.booking.schedule.mapper.ScheduleMapper;
import com.flight.booking.schedule.model.Seat;
import com.flight.booking.schedule.repository.ScheduleRepository;
import com.flight.booking.schedule.websocket.SeatWebSocketHandler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final SeatWebSocketHandler seatWebSocketHandler;

    @Override
    public ScheduleResponse createSchedule(ScheduleRequest schedule) {
        return scheduleMapper
                .mapToDTO(scheduleRepository.save(scheduleMapper.mapToModel(schedule)),false);
    }

    @Override
    public List<ScheduleResponse> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        if(schedules.isEmpty()){
            throw new NoScheduleFoundException("There are no schedules yet!");
        }
        return  schedules.stream().map(schedule -> scheduleMapper.mapToDTO(schedule,false)).toList();
    }

    public List<ScheduleResponse> getSchedulesByDateTime(Instant start, Instant end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Start and end time must be provided.");
        }
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start time cannot be after end time.");
        }
        System.out.println("🔍 API Received Start: " + start);
        System.out.println("🔍 API Received End: " + end);
        List<Schedule> schedules = scheduleRepository.findSchedulesBetween(start, end);

        if (schedules.isEmpty()) {
            throw new NoScheduleFoundException("No schedules found for the given time range.");
        }

        return schedules.stream()
                .map(schedule -> scheduleMapper.mapToDTO(schedule, false))
                .toList();
    }

    @Override
    public void deleteScheduleById(String id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public void deleteAllSchedules() {
        scheduleRepository.deleteAll();
    }

    @Override
    public ScheduleResponse getScheduleById(String id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(()-> new NoScheduleFoundException("Schedule Doesn't Exist"));
        return scheduleMapper.mapToDTO(schedule,true);
    }

    @Override
    public ScheduleResponse updateSchedule(ScheduleRequest scheduleRequest) {
        Schedule existingSchedule = scheduleRepository.findById(scheduleRequest.getId())
                .orElseThrow(() -> new NoScheduleFoundException("Cannot update. Schedule not found with ID: " + scheduleRequest.getId()));
        existingSchedule.setFlightId(scheduleRequest.getFlightId());
        existingSchedule.setSourceAirportId(scheduleRequest.getSourceAirport());
        existingSchedule.setDestinationAirportId(scheduleRequest.getDestinationAirport());
        existingSchedule.setDateTime(scheduleRequest.getDateTime());
        return scheduleMapper.mapToDTO(scheduleRepository.save(existingSchedule),false);
    }

    @Override
    public void deleteByAirportId(int id) {
        scheduleRepository.deleteBySourceAirportIdOrDestinationAirportId(id,id);
    }

    @Override
    public void deleteByFlightId(String id) {
        scheduleRepository.deleteByFlightId(id);
    }

    @Override
    public boolean validateBookingRequest(BookingRequest bookingRequest) {
        Schedule schedule = scheduleRepository.findById(bookingRequest.getScheduleId()).orElse(null);
        if(schedule == null){
            return false;
        }

        List<Seat> seats = schedule.getSeats();
        List<Integer> requestedSeatNumbers = bookingRequest.getSeatNumbers();

        for (Integer seatNumber : requestedSeatNumbers) {
            // Find the seat with the requested number
            boolean seatFound = false;
            for (Seat seat : seats) {
                if (seat.getSeatNumber() == seatNumber) {
                    seatFound = true;
                    // Check if the seat is VACANT
                    if (seat.getStatus() != SeatStatus.VACANT) {
                        return false; // Seat is not VACANT
                    }
                    break;
                }
            }
            if (!seatFound) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean bookSeats(BookingRequest bookingRequest) {
        Schedule schedule = scheduleRepository.findById(bookingRequest.getScheduleId()).orElse(null);
        if(schedule == null){
            return false;
        }

        List<Seat> seats = schedule.getSeats();
        List<Integer> requestedSeatNumbers = bookingRequest.getSeatNumbers();
        List<Integer> bookedSeats = new ArrayList<>();

        for (Integer seatNumber : requestedSeatNumbers) {
            // Find the seat with the requested number
            boolean seatFound = false;
            for (Seat seat : seats) {
                if (seat.getSeatNumber() == seatNumber) {
                    seatFound = true;
                    seat.setStatus(SeatStatus.BOOKED);
                    bookedSeats.add(seatNumber);
                    break;
                }
            }
            if (!seatFound) {
                return false;
            }
        }
        scheduleRepository.save(schedule);
        // Broadcast booked seats after successful booking
        if (!bookedSeats.isEmpty()) {
            try {
                seatWebSocketHandler.broadcastBookedSeats(bookingRequest.getScheduleId(), bookedSeats);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void deletePastSchedules() {
        Instant now = Instant.now();
        System.out.println("🗑️ Cleaning up past schedules at: " + now);
        scheduleRepository.cleanSchedules(now);
    }
}
