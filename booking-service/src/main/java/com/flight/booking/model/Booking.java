package com.flight.booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "booking")
public class Booking {
    @Id
    private String id;
    private String scheduleId;
    private int userId;
    private List<Integer> seatNumbers;
    private Integer amount;
    private Instant dateTime;
    private String razorPayOrderId;
    private String razorpayPaymentId;
}
