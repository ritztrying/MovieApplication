package com.platform.movie.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BookingRequestDTO {
    private Long userId;
    private Long showId;
    private List<Long> seatIds;
}
