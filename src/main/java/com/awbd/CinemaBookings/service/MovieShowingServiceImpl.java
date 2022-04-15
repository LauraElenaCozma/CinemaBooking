package com.awbd.CinemaBookings.service;

import com.awbd.CinemaBookings.domain.MovieShowing;
import com.awbd.CinemaBookings.exception.MovieShowingNotFoundException;
import com.awbd.CinemaBookings.repository.MovieShowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieShowingServiceImpl implements MovieShowingService {

    MovieShowingRepository movieShowingRepository;

    @Autowired
    public MovieShowingServiceImpl(MovieShowingRepository movieShowingRepository) {
        this.movieShowingRepository = movieShowingRepository;
    }

    @Override
    public List<MovieShowing> findAll() {
        List<MovieShowing> movieShowings = new LinkedList<>();
        movieShowingRepository.findAll().iterator().forEachRemaining(movieShowings::add);
        return movieShowings;
    }

    @Override
    public Page<MovieShowing> findAllPages(Pageable pageable) {
        return movieShowingRepository.findAll(pageable);
    }

    @Override
    public Page<MovieShowing> findAllPagesByMovieId(Long movieId, Pageable pageable) {
        return movieShowingRepository.findAllByMovie_Id(movieId, pageable);
    }

    @Override
    public MovieShowing findById(Long id) {
        return movieShowingRepository.findById(id).orElseThrow(() -> new MovieShowingNotFoundException(id));
    }

    @Override
    public MovieShowing save(MovieShowing movieShowing) {
        return movieShowingRepository.save(movieShowing);
    }

    @Override
    public void deleteById(Long id) {
        movieShowingRepository.deleteById(id);
    }

    @Override
    public Integer getNumberOfAvailableSeats(Long showingId) {
        Optional<MovieShowing> optionalMovieShowing = movieShowingRepository.findById(showingId);
        if(optionalMovieShowing.isEmpty())
            throw new MovieShowingNotFoundException(showingId);
        MovieShowing movieShowing = optionalMovieShowing.get();
        Integer numSoldSeats = movieShowingRepository.getNumberOfSoldSeats(showingId);
        if(numSoldSeats == null)
            numSoldSeats = 0;
        return movieShowing.getVenue().getSeatCapacity() - numSoldSeats;
    }
}
