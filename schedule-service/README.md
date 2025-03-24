# Schedule Service Documentation

## Overview
The Schedule Service manages flight schedules in the aviation ticketing system. It handles the creation, management, and coordination of flight schedules while maintaining integration with the Flight Service.

## Core Components

### 1. ScheduleService Interface
- Defines core schedule management operations
- Methods:
    - `createSchedule(ScheduleRequest)` - Create new schedule
    - `getAllSchedules()` - Retrieve all schedules
    - `deleteScheduleById(String)` - Delete specific schedule
    - `deleteAllSchedules()` - Delete all schedules
    - `getScheduleById(String)` - Get schedule by ID
    - `updateSchedule(ScheduleRequest)` - Update schedule
    - `deleteByAirportId(int)` - Delete schedules by airport
    - `deleteByFlightId(String)` - Delete schedules by flight

### 2. ScheduleServiceImpl
- Implements ScheduleService interface
- Features:
    - Schedule CRUD operations
    - Integration with Flight Service
    - Schedule validation and error handling
    - Mapping between DTOs and entities

### 3. FlightServiceCommunicator (Feign Client)
- Interfaces with Flight Service
- Operations:
    - Get flight details by ID

## Data Model

### Schedule Entity
- Schedule ID
- Flight details
- Source and destination airports
- Departure and arrival times
- Price information

### Seat Model
- Seat number
- Booking status

## Dependencies
- Spring Boot Starter Web
- Spring Cloud Netflix Eureka Client
- Spring Cloud OpenFeign
- Spring Boot MongoDB Starter
- Lombok

## Features
- Complete schedule management
- Seat management system
- Integration with Flight Service
- MongoDB-based persistence
- Service discovery integration
- DTO pattern for data transfer

## Error Handling
- NoScheduleFoundException
- InvalidCredentials
- CommunicationFailedException
- Validation errors

## Integration Points
- Flight Service for flight validation
- MongoDB for data persistence
- Service Registry for service discovery

## Configuration
- MongoDB configuration
- Eureka client configuration
- Feign client setup
- Exception handling setup 