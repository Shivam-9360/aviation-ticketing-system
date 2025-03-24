# Flight Service Documentation

## Overview
The Flight Service manages flight-related operations in the aviation ticketing system. It handles flight creation, retrieval, updates, and deletion while maintaining communication with the Schedule Service for coordinated operations.

## Core Components

### 1. FlightService Interface
- Defines core flight management operations
- Methods:
    - `createFlight(FlightRequest)` - Create new flight
    - `getFlightById(String)` - Retrieve flight by ID
    - `getAllFlights()` - Get all flights
    - `updateFlight(FlightRequest)` - Update flight details
    - `deleteFlightById(String)` - Delete specific flight
    - `deleteAllFlights()` - Delete all flights

### 2. FlightServiceImpl
- Implements FlightService interface
- Features:
    - Flight CRUD operations
    - Integration with Schedule Service
    - Data validation and error handling
    - Flight mapping between DTOs and entities

### 3. ScheduleServiceCommunicator (Feign Client)
- Handles communication with Schedule Service
- Operations:
    - Delete schedules by flight ID
    - Delete all schedules

## Data Model
Flight entity includes:
- Flight ID
- Flight Number
- Total Seats
- Company
- Seat Type

## Dependencies
- Spring Boot Starter Web
- Spring Cloud Netflix Eureka Client
- Spring Cloud OpenFeign
- Spring Data JPA
- Lombok

## Features
- Complete flight management system
- Automatic schedule cleanup on flight deletion
- Data validation and error handling
- Service discovery integration
- DTO pattern for data transfer

## Error Handling
- FlightNotFoundException
- CommunicationFailedException for service communication issues
- Validation errors for invalid requests

## Integration Points
- Schedule Service for coordinated deletions
- Service Registry for service discovery
- Database for flight data persistence

## Configuration
- Configured as Eureka client
- Uses Feign for inter-service communication
- Database configuration for flight storage 