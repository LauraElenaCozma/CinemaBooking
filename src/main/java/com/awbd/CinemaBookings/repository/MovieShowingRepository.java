package com.awbd.CinemaBookings.repository;

import com.awbd.CinemaBookings.domain.Movie;
import com.awbd.CinemaBookings.domain.MovieShowing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieShowingRepository extends PagingAndSortingRepository<MovieShowing, Long> {
    Page<MovieShowing> findAllByMovie_Id(Long movieId, Pageable pageable);
    Page<MovieShowing> findAll(Pageable pageable);

    @Query("SELECT SUM(b.numReservedSeats) FROM Booking b " +
            "JOIN MovieShowing ms ON b.movieShowing.id = ms.id " +
            "WHERE ms.id = :showingId")
    Integer getNumberOfSoldSeats(@Param("showingId") Long showingId);
}
