package com.platform.movie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "shows")
public class Show {
    @Id
    @GeneratedValue
    private Long id;

    private Long movieId;
    private Long theatreId;

    private LocalDate showDate;
    private LocalTime showTime;
}
