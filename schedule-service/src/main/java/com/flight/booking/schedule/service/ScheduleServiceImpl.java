package com.flight.booking.schedule.service;

import com.flight.booking.schedule.dto.*;
import com.flight.booking.schedule.dto.ScheduleRequest;
import com.flight.booking.schedule.dto.ScheduleResponse;
import com.flight.booking.schedule.feign.FlightServiceCommunicator;
import com.flight.booking.schedule.model.Schedule;
import com.flight.booking.schedule.exception.NoScheduleFoundException;
import com.flight.booking.schedule.mapper.ScheduleMapper;
import com.flight.booking.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    public ScheduleRepository scheduleRepository;

    @Autowired
    public FlightServiceCommunicator flightServiceCommunicator;

    private final ScheduleMapper scheduleMapper;

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
        System.out.println("here");
        scheduleRepository.deleteByFlightId(id);
    }
}
