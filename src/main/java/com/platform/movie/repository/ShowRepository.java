package com.platform.movie.repository;

import com.platform.movie.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

    @Query("SELECT s FROM Show s WHERE s.movieId = :movieId AND s.showDate = :date")
    List<Show> findShowsByMovieAndDate(Long movieId, LocalDate date);
}
