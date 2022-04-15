package com.awbd.CinemaBookings.service;

import com.awbd.CinemaBookings.domain.MovieShowing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieShowingService {
    List<MovieShowing> findAll();
    Page<MovieShowing> findAllPages(Pageable pageable);
    Page<MovieShowing> findAllPagesByMovieId(Long movieId, Pageable pageable);
    MovieShowing findById(Long id);
    MovieShowing save(MovieShowing movieShowing);
    void deleteById(Long id);
    public Integer getNumberOfAvailableSeats(Long showingId);
}
