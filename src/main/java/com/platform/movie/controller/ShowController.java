package com.platform.movie.controller;

import com.platform.movie.dto.TheatreShowsDTO;
import com.platform.movie.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class ShowController {

    @Autowired
    private ShowService showService;

    @GetMapping("/{movieId}/shows")
    public ResponseEntity<List<TheatreShowsDTO>> getAvailableShow(
            @PathVariable Long movieId,
            @RequestParam(name = "city") String city,
            @RequestParam(name = "date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<TheatreShowsDTO> result = showService.getShows(movieId, city, date);
        return ResponseEntity.ok(result);
    }
}
