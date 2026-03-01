package com.platform.movie.entity;

import com.platform.movie.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Seat {
    @Id
    @GeneratedValue
    private Long id;

    private Long showId;
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus status; // AVAILABLE, BOOKED
}
