# Flight Booking System

## Overview

The Flight Booking System is a microservices-based application designed to manage flight bookings, user authentication, and airport information. It leverages Spring Boot and Spring Cloud for building scalable and resilient services.

## Microservices

The project consists of the following microservices:

1. **User Service**: Manages user data and operations.
2. **Auth Service**: Handles authentication and authorization.
3. **Flight Service**: Manages flight information and operations.
4. **Airport Service**: Manages airport data.
5. **API Gateway**: Acts as a single entry point for all client requests.
6. **Eureka Server**: Service registry for service discovery.

## Technologies Used

- **Spring Boot**: For building microservices.
- **Spring Cloud**: For service discovery, configuration, and gateway.
- **Spring Security**: For securing the application.
- **MongoDB**: For storing flight data.
- **MySQL**: For storing user and airport data.
- **JWT**: For token-based authentication.
- **Lombok**: For reducing boilerplate code.
- **Maven**: For project management and build.

## Project Structure

- **api-gateway**: Contains the API Gateway service.
- **auth-service**: Contains the authentication service.
- **eureka-server**: Contains the Eureka Server for service discovery.
- **flight-service**: Contains the flight management service.
- **airport-service**: Contains the airport management service.
- **user-service**: Contains the user management service.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- MongoDB
- MySQL

### Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```bash
   cd flight-booking-system
   ```

3. Build the project using Maven:
   ```bash
   mvn clean install
   ```

### Running the Services

1. **Start the Eureka Server**:
   ```bash
   cd eureka-server
   mvn spring-boot:run
   ```

2. **Start the API Gateway**:
   ```bash
   cd api-gateway
   mvn spring-boot:run
   ```

3. **Start the Auth Service**:
   ```bash
   cd auth-service
   mvn spring-boot:run
   ```

4. **Start the User Service**:
   ```bash
   cd user-service
   mvn spring-boot:run
   ```

5. **Start the Flight Service**:
   ```bash
   cd flight-service
   mvn spring-boot:run
   ```

6. **Start the Airport Service**:
   ```bash
   cd airport-service
   mvn spring-boot:run
   ```

### Configuration

- **Database Configuration**: Update the `application.yaml` files in each service to configure database connections.
- **Eureka Configuration**: Ensure all services are registered with the Eureka Server.

### API Endpoints

- **User Service**: `/api/users`, `/api/user/{id}`
- **Auth Service**: `/api/login`, `/api/register`
- **Flight Service**: `/api/flights`, `/api/flight/{id}`
- **Airport Service**: `/api/airports`, `/api/airport/{id}`
