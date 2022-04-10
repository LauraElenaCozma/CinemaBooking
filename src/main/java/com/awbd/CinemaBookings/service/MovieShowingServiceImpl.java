package com.awbd.CinemaBookings.service;

import com.awbd.CinemaBookings.domain.MovieShowing;
import com.awbd.CinemaBookings.repository.MovieShowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

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
    public MovieShowing findById(Long id) {
        return movieShowingRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public MovieShowing save(MovieShowing movieShowing) {
        return movieShowingRepository.save(movieShowing);
    }

    @Override
    public void deleteById(Long id) {
        movieShowingRepository.deleteById(id);
    }
}
