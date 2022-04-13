package com.awbd.CinemaBookings.service;

import com.awbd.CinemaBookings.domain.Actor;
import com.awbd.CinemaBookings.domain.Movie;
import com.awbd.CinemaBookings.exception.MovieNotFoundException;
import com.awbd.CinemaBookings.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{

    MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> movies = new LinkedList<>();
        movieRepository.findAll().iterator().forEachRemaining(movies::add);
        return movies;
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Long id, Movie updatedMovie) {
        Optional<Movie> movie = movieRepository.findById(id);
        if(movie.isEmpty())
            throw new MovieNotFoundException(id);
        Movie newMovie = movie.get();
        newMovie.setTitle(updatedMovie.getTitle());
        newMovie.setDuration(updatedMovie.getDuration());
        newMovie.setGenre(updatedMovie.getGenre());
        newMovie.setMovieDetails(updatedMovie.getMovieDetails());

        return movieRepository.save(newMovie);
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }
}
