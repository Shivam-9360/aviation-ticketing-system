package com.flight.booking.schedule.controller;

import com.flight.booking.schedule.dto.BookingRequest;
import com.flight.booking.schedule.dto.DTO;
import com.flight.booking.schedule.dto.ScheduleRequest;
import com.flight.booking.schedule.dto.ScheduleResponse;
import com.flight.booking.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedule")
    public ResponseEntity<DTO<ScheduleResponse>> createSchedule(@RequestBody ScheduleRequest schedule){
        ScheduleResponse createdSchedule = scheduleService.createSchedule(schedule);
        return ResponseEntity.ok(DTO.<ScheduleResponse>builder()
                        .success(true)
                        .message("Schedule Created")
                        .data(createdSchedule)
                .build());
    }
    @PutMapping("/schedule")
    public ResponseEntity<DTO<ScheduleResponse>> updateSchedule(@RequestBody ScheduleRequest schedule){
        ScheduleResponse createdSchedule = scheduleService.updateSchedule(schedule);
        return ResponseEntity.ok(DTO.<ScheduleResponse>builder()
                .success(true)
                .message("Schedule Updated")
                .data(createdSchedule)
                .build());
    }
    @GetMapping("/schedules")
    public ResponseEntity<DTO<List<ScheduleResponse>>> getSchedules(
            @RequestParam(name = "start") String start,
            @RequestParam(name = "end") String end) {
        Instant startDateTime = Instant.parse(start);
        Instant endDateTime = Instant.parse(end);


        List<ScheduleResponse> schedules = scheduleService.getSchedulesByDateTime(startDateTime,endDateTime);
        return ResponseEntity.ok(DTO.<List<ScheduleResponse>>builder()
                .success(true)
                .message("Schedules Fetched Successfully")
                .data(schedules)
                .build());
    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity<DTO<ScheduleResponse>> getScheduleById(@PathVariable("id") String id){
        ScheduleResponse schedule = scheduleService.getScheduleById(id);
        return ResponseEntity.ok(DTO.<ScheduleResponse>builder()
                .success(true)
                .message("Schedules Fetched Successfully")
                .data(schedule)
                .build());
    }
    @GetMapping("/all-schedules")
    public ResponseEntity<DTO<List<ScheduleResponse>>> getAllSchedules(){
        List<ScheduleResponse> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(DTO.<List<ScheduleResponse>>builder()
                .success(true)
                .message("Schedules Fetched Successfully")
                .data(schedules)
                .build());
    }
    @DeleteMapping("/schedules")
    public ResponseEntity<DTO<String>> deleteAllSchedules (){
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

    @PostMapping("/validate-booking")
    public DTO<String> validateBookingRequest(@RequestBody BookingRequest bookingRequest){
        boolean isValid = scheduleService.validateBookingRequest(bookingRequest);
        return DTO.<String>builder()
                .success(isValid)
                .data(null)
                .message(isValid?"Booking Request Valid":"Booking Request Invalid")
                .build();
    }

    @PostMapping("/book-seats")
    public DTO<String> bookSeats(@RequestBody BookingRequest bookingRequest){
        boolean isBooked = scheduleService.bookSeats(bookingRequest);
        return DTO.<String>builder()
                .success(isBooked)
                .data(null)
                .message(isBooked?"Booking Successful":"Booking Failed")
                .build();
    }
}
