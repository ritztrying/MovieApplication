package com.platform.movie.dto;

import lombok.Data;

import java.util.List;

@Data
public class TheatreShowsDTO {
    private String theatreName;
    private List<ShowDTO> shows;

    public TheatreShowsDTO(String theatreName, List<ShowDTO> shows) {
        this.theatreName = theatreName;
        this.shows = shows;
    }
}
