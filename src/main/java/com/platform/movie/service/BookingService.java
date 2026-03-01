package com.platform.movie.service;

import com.platform.movie.dto.BookingRequestDTO;
import com.platform.movie.dto.BookingResponseDTO;
import com.platform.movie.entity.Booking;
import com.platform.movie.entity.BookingSeat;
import com.platform.movie.entity.Seat;
import com.platform.movie.enums.BookingStatus;
import com.platform.movie.enums.SeatStatus;
import com.platform.movie.repository.BookingRepository;
import com.platform.movie.repository.BookingSeatRepository;
import com.platform.movie.repository.SeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingSeatRepository bookingSeatRepository;

    @Transactional
    public BookingResponseDTO bookTickets(BookingRequestDTO request) {

        List<Seat> seats = seatRepository.findSeatsForUpdate(
                request.getSeatIds(),
                request.getShowId()
        );

        for (Seat seat : seats) {
            if (seat.getStatus() != SeatStatus.AVAILABLE) {
                throw new RuntimeException("Seat already booked: " + seat.getId());
            }
        }

        seats.forEach(seat -> seat.setStatus(SeatStatus.BOOKED));
        seatRepository.saveAll(seats);

        Booking booking = new Booking();
        booking.setUserId(request.getUserId());
        booking.setShowId(request.getShowId());
        booking.setStatus(BookingStatus.CONFIRMED);

        int totalAmount = seats.size() * 250; // assume ₹250 per seat
        booking.setTotalAmount(totalAmount);

        booking = bookingRepository.save(booking);

        Booking finalBooking = booking;
        List<BookingSeat> bookingSeats = seats.stream()
                .map(seat -> {
                    BookingSeat bs = new BookingSeat();
                    bs.setBookingId(finalBooking.getId());
                    bs.setSeatId(seat.getId());
                    return bs;
                }).toList();

        bookingSeatRepository.saveAll(bookingSeats);

        return new BookingResponseDTO(
                booking.getId(),
                booking.getStatus().name(),
                booking.getTotalAmount()
        );
    }
}
