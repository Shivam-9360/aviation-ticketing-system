package com.flight.booking.service;

import com.flight.booking.dto.BookingRequest;
import com.flight.booking.dto.BookingResponse;
import com.flight.booking.dto.PaymentRequest;
import com.flight.booking.dto.PaymentResponse;
import com.flight.booking.feign.PaymentServiceCommunicator;
import com.flight.booking.feign.ScheduleServiceCommunicator;
import com.flight.booking.mapper.BookingMapper;
import com.flight.booking.model.Booking;
import com.flight.booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final PaymentServiceCommunicator paymentServiceCommunicator;
    private final ScheduleServiceCommunicator scheduleServiceCommunicator;
    private final BookingMapper bookingMapper;

    @Override
    public PaymentResponse getOrderId(BookingRequest bookingRequest) {
        try{
            boolean isValid = validateBookingRequest(bookingRequest);
            if (!isValid) {
                throw new RuntimeException("Invalid Booking Request");
            }
            return paymentServiceCommunicator.getOrderId(
                    PaymentRequest.builder()
                            .amount(bookingRequest.getAmount())
                            .userId(bookingRequest.getUserId())
                            .currency("INR")
                    .build()).getData();
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.toString());
            throw new RuntimeException("Payment Service Unavailable");
        }
    }

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        scheduleServiceCommunicator.bookSeats(bookingRequest);
        return bookingMapper.mapToDTO(
                bookingRepository.save(bookingMapper.mapToModel(bookingRequest))
        );
    }

    @Override
    public List<BookingResponse> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return  bookings.stream().map(bookingMapper::mapToDTO).toList();
    }

    public boolean validateBookingRequest(BookingRequest bookingRequest) {
        try {
            return scheduleServiceCommunicator.validateBookingRequest(bookingRequest).isSuccess();
        } catch (Exception e) {
            return false;
        }
    }
}
