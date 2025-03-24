# Airport Service Documentation

## Overview
The Airport Service manages airport-related operations in the aviation ticketing system. It handles airport information management and coordinates with the Schedule Service for flight schedule management.

## Core Components

### 1. AirportService Interface
- Defines core airport management operations
- Methods:
    - `getAllAirports()` - Retrieve all airports
    - `createAirport(AirportRequest)` - Create new airport
    - `deleteAirportById(int)` - Delete specific airport
    - `getAirportById(int)` - Get airport by ID
    - `updateAirport(AirportRequest)` - Update airport details

### 2. AirportServiceImpl
- Implements AirportService interface
- Features:
    - Airport CRUD operations
    - Integration with Schedule Service
    - Data validation and error handling
    - Airport mapping between DTOs and entities

### 3. ScheduleServiceCommunicator (Feign Client)
- Interfaces with Schedule Service
- Operations:
    - Delete schedules by airport ID

## Data Model
Airport entity includes:
- Airport ID
- Airport Name
- Location details
- Other airport-specific information

## Dependencies
- Spring Boot Starter Web
- Spring Cloud Netflix Eureka Client
- Spring Cloud OpenFeign
- Spring Data JPA
- Lombok

## Features
- Complete airport management system
- Integration with Schedule Service
- Data validation and error handling
- Service discovery integration
- DTO pattern for data transfer

## Error Handling
- AirportDoesntExistException
- CommunicationFailedException
- Validation errors for invalid requests

## Integration Points
- Schedule Service for coordinated operations
- Service Registry for service discovery
- Database for airport data persistence

## Configuration
- Database configuration
- Eureka client configuration
- Feign client setup
- Exception handling setup
- Mapper configuration
# Spring Boot

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.3/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.3/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.3/reference/web/servlet.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.4.3/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.4.3/reference/using/devtools.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

