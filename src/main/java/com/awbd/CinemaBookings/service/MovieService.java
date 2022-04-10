package com.awbd.CinemaBookings.service;


import com.awbd.CinemaBookings.domain.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();
    Movie findById(Long id);
    Movie save(Movie movie);
    Movie update(Long id, Movie updatedMovie);
    void deleteById(Long id);
}
