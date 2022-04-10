package com.awbd.CinemaBookings.service;

import com.awbd.CinemaBookings.domain.MovieShowing;

import java.util.List;

public interface MovieShowingService {
    List<MovieShowing> findAll();
    MovieShowing findById(Long id);
    MovieShowing save(MovieShowing movieShowing);
    void deleteById(Long id);
}
