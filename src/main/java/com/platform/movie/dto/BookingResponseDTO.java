package com.platform.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingResponseDTO {
    private Long bookingId;
    private String status;
    private Integer totalAmount;
}
