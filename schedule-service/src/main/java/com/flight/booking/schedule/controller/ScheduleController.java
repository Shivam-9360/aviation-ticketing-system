package com.flight.booking.schedule.controller;

import com.flight.booking.schedule.dto.DTO;
import com.flight.booking.schedule.dto.ScheduleRequest;
import com.flight.booking.schedule.dto.ScheduleResponse;
import com.flight.booking.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleController {
    public final ScheduleService scheduleService;

    @PostMapping("/schedule")
    public ResponseEntity<DTO<ScheduleResponse>> createFlight(@RequestBody ScheduleRequest schedule){
        ScheduleResponse createdSchedule = scheduleService.createSchedule(schedule);
        return ResponseEntity.ok(DTO.<ScheduleResponse>builder()
                        .success(true)
                        .message("Schedule Created")
                        .data(createdSchedule)
                .build());
    }

    @GetMapping("/schedules")
    public ResponseEntity<DTO<List<ScheduleResponse>>> getAllFlights(){
        List<ScheduleResponse> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(DTO.<List<ScheduleResponse>>builder()
                .success(true)
                .message("Schedules Fetched Successfully")
                .data(schedules)
                .build());
    }
    @DeleteMapping("/schedules")
    public ResponseEntity<DTO<String>> deleteAllFlights (){
        scheduleService.deleteAllSchedules();
        return ResponseEntity.ok(DTO.<String>builder()
                .success(true)
                .message("Schedules Deleted Successfully")
                .data(null)
                .build());
    }

    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<DTO<String>> deleteScheduleById(@PathVariable("id") String id){
        scheduleService.deleteScheduleById(id);
        return  ResponseEntity.ok(DTO.<String>builder()
                .success(true)
                .message("Schedule Deleted Successfully")
                .data(null)
                .build());
    }

    @Transactional
    @DeleteMapping("/schedule/airport/{id}")
    public ResponseEntity<DTO<String>> deleteByAirportId (@PathVariable("id") int id){
        scheduleService.deleteByAirportId(id);
        return ResponseEntity.ok(DTO.<String>builder()
                .success(true)
                .message("Schedule Deleted Successfully")
                .data(null)
                .build());
    }
    @Transactional
    @DeleteMapping("/schedule/flight/{id}")
    public ResponseEntity<DTO<String>> deleteByFlightId (@PathVariable("id") String id){
        scheduleService.deleteByFlightId(id);
        return ResponseEntity.ok(DTO.<String>builder()
                .success(true)
                .message("Schedule Deleted Successfully")
                .data(null)
                .build());
    }
}
