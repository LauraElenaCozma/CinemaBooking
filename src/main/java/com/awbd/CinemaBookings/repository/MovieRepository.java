package com.awbd.CinemaBookings.repository;

import com.awbd.CinemaBookings.domain.Movie;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface MovieRepository extends CrudRepository<Movie, Long> {
    List<Movie> findAllByTitle(String title);
    List<Movie> findAllByIdIn(List<Long> ids);
}
