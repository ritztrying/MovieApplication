package com.platform.movie.dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ShowDTO {
    private Long showId;
    private LocalTime time;

    public ShowDTO(Long showId, LocalTime time) {
        this.showId = showId;
        this.time = time;
    }

}
