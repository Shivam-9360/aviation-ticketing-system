# Payment Service Documentation

## Overview
The Payment Service handles payment processing for the aviation ticketing system. It integrates with Razorpay payment gateway to create payment orders and verify payment status.

## Core Components

### 1. PaymentService Interface
- Defines core payment processing operations
- Methods:
    - `createOrder(PaymentRequest)` - Creates a payment order in Razorpay
    - `verifyPayment(VerificationRequest)` - Verifies payment signatures and status

### 2. PaymentServiceImpl
- Implements PaymentService interface
- Features:
    - Razorpay integration
    - Payment order creation
    - Digital signature verification
    - Secure payment handling

### 3. PaymentController
- REST controller for payment operations
- Endpoints:
    - POST `/api/order` - Create a new payment order
    - POST `/api/verify` - Verify payment status and signature

## Service Communication

### Synchronous Communication
- **BookingService → PaymentService**: Booking service calls PaymentService to create payment orders

## Data Model

### DTO Classes
- PaymentRequest: Payment details for creating order
- PaymentResponse: Contains payment order ID and other details
- VerificationRequest: Contains data for payment verification 
- DTO: Generic response wrapper with success/failure status

## Dependencies
- Spring Boot Starter Web
- Spring Cloud Netflix Eureka Client
- Razorpay Java SDK
- Lombok

## Features
- Razorpay payment gateway integration
- Secure order creation
- Payment verification using cryptographic signatures
- Standardized DTO responses

## Error Handling
- Payment exception handling
- Razorpay API exceptions
- Verification failures

## Integration Points
- Razorpay API for payment processing
- Booking Service for order creation
- Service Registry for service discovery

## Configuration
- Server port: 9006
- Razorpay API key and secret configuration
- Eureka client configuration

## Payment Flow Example

### Payment Processing Flow:
1. Booking Service requests payment order creation
2. Payment Service creates order with Razorpay
3. User completes payment on client side
4. Client sends verification request to Payment Service
5. Payment Service verifies payment signature and status
6. Payment Service returns verification result to client
7. Client updates booking status with Booking Service

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

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Service Registration and Discovery with Eureka and Spring Cloud](https://spring.io/guides/gs/service-registration-and-discovery/)

### Additional Links
These additional references should also help you:

* [Declarative REST calls with Spring Cloud OpenFeign sample](https://github.com/spring-cloud-samples/feign-eureka)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides. 