package com.awbd.CinemaBookings.repository;

import com.awbd.CinemaBookings.domain.MovieShowing;
import org.springframework.data.repository.CrudRepository;

public interface MovieShowingRepository extends CrudRepository<MovieShowing, Long> {
}
