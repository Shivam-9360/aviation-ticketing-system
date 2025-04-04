# Booking Service Documentation

## Overview
The Booking Service is responsible for managing flight bookings in the aviation ticketing system. It handles the creation and retrieval of bookings and integrates with the Payment Service for payment processing and Schedule Service for seat management.

## Core Components

### 1. BookingService Interface
- Defines core booking management operations
- Methods:
    - `getOrderId(BookingRequest)` - Creates a payment order for a booking
    - `createBooking(BookingRequest)` - Creates a new booking record
    - `getAllBookings()` - Retrieves all bookings
    - `getBookingByUserId(int)` - Retrieves bookings for a specific user

### 2. BookingServiceImpl
- Implements BookingService interface
- Features:
    - Booking CRUD operations
    - Integration with Payment Service
    - User-based booking management
    - Response formatting

### 3. BookingController
- REST controller for booking operations
- Endpoints:
    - POST `/api/create-order` - Generate payment order
    - POST `/api/booking` - Create booking record
    - GET `/api/bookings/user/{userId}` - Get user-specific bookings
    - GET `/api/bookings` - Get all bookings

## Service Communication

### Synchronous Communication (Feign Clients)
- **BookingService → PaymentService**: Creating payment orders
- **BookingService → ScheduleService**: Checking seat availability
- **BookingService → UserService**: Validating user information

## Data Model

### Booking Entity
- Booking ID
- User information
- Flight and schedule details
- Payment information
- Booking status
- Creation timestamp

### DTO Classes
- BookingRequest: Contains booking details from client
- BookingResponse: Response data for booking operations
- PaymentRequest: Payment request data
- PaymentResponse: Payment details response

## Dependencies
- Spring Boot Starter Web
- Spring Cloud Netflix Eureka Client
- Spring Cloud OpenFeign
- Spring Boot MongoDB Starter
- Lombok

## Features
- Complete booking management
- User-based booking tracking
- Integration with Payment Service
- MongoDB-based persistence
- Service discovery integration
- DTO pattern for data transfer

## Error Handling
- Exception handling for booking operations
- Payment validation errors
- User validation

## Integration Points
- Payment Service for payment processing
- Schedule Service for seat management
- User Service for user validation
- MongoDB for data persistence
- Service Registry for service discovery

## Configuration
- Server port: 9005
- MongoDB configuration
- Eureka client configuration

## Booking Flow Example

### Booking Flow with Payment:
1. User initiates booking → BookingController receives request
2. BookingService validates with ScheduleService (Feign)
3. BookingService creates payment order with PaymentService (Feign)
4. User completes payment on client side
5. Client sends verification request to PaymentService
6. PaymentService verifies payment and returns status
7. BookingService updates booking status
8. BookingService updates seat availability with ScheduleService (Feign)

# Spring Boot

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.3/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.3/maven-plugin/build-image.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.4.3/reference/using/devtools.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.3/reference/web/servlet.html)
* [OpenFeign](https://docs.spring.io/spring-cloud-openfeign/reference/)
* [Eureka Discovery Client](https://docs.spring.io/spring-cloud-netflix/reference/spring-cloud-netflix.html#_service_discovery_eureka_clients)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/3.4.3/reference/data/nosql/mongodb.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Service Registration and Discovery with Eureka and Spring Cloud](https://spring.io/guides/gs/service-registration-and-discovery/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)

### Additional Links
These additional references should also help you:

* [Declarative REST calls with Spring Cloud OpenFeign sample](https://github.com/spring-cloud-samples/feign-eureka)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides. 