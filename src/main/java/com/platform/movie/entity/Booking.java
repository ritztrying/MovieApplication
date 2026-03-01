package com.platform.movie.entity;

import com.platform.movie.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Booking {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private Long showId;
    private Integer totalAmount;

    @Enumerated(EnumType.STRING)
    private BookingStatus status; // PENDING, CONFIRMED
}