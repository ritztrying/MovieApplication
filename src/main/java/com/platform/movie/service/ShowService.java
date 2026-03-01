package com.platform.movie.service;

import com.platform.movie.dto.ShowDTO;
import com.platform.movie.dto.TheatreShowsDTO;
import com.platform.movie.entity.Show;
import com.platform.movie.entity.Theatre;
import com.platform.movie.repository.ShowRepository;
import com.platform.movie.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ShowService {

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private ShowRepository showRepository;

    public List<TheatreShowsDTO> getShows(Long movieId, String city, LocalDate date) {
        List<Theatre> theatres = theatreRepository.findByCity(city);
        List<Show> shows = showRepository.findShowsByMovieAndDate(movieId, date);

        Map<Long, String> theatreMap = theatres.stream()
                .collect(Collectors.toMap(Theatre::getId, Theatre::getName));

        Map<Long, List<Show>> showsByTheatre = shows.stream()
                .filter(show -> theatreMap.containsKey(show.getTheatreId()))
                .collect(Collectors.groupingBy(Show::getTheatreId));

        List<TheatreShowsDTO> response = new ArrayList<>();

        for (Map.Entry<Long, List<Show>> entry : showsByTheatre.entrySet()) {
            Long theatreId = entry.getKey();
            List<ShowDTO> showDTOs = entry.getValue().stream()
                    .map(s -> new ShowDTO(s.getId(), s.getShowTime()))
                    .collect(Collectors.toList());

            response.add(new TheatreShowsDTO(
                    theatreMap.get(theatreId),
                    showDTOs
            ));
        }

        return response;
    }
}
