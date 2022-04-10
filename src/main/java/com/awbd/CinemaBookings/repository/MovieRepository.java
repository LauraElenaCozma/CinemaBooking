package com.awbd.CinemaBookings.repository;

import com.awbd.CinemaBookings.domain.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
}
