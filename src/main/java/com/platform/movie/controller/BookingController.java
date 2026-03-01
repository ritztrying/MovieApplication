package com.platform.movie.controller;

import com.platform.movie.dto.BookingRequestDTO;
import com.platform.movie.dto.BookingResponseDTO;
import com.platform.movie.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponseDTO> bookTickets(@RequestBody BookingRequestDTO request) {
        return ResponseEntity.ok(bookingService.bookTickets(request));
    }
}
