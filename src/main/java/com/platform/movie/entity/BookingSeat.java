package com.platform.movie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class BookingSeat {
    @Id
    @GeneratedValue
    private Long id;

    private Long bookingId;
    private Long seatId;
}
