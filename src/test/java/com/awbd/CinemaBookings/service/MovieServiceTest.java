package com.awbd.CinemaBookings.service;

import com.awbd.CinemaBookings.domain.Genre;
import com.awbd.CinemaBookings.domain.Info;
import com.awbd.CinemaBookings.domain.Movie;
import com.awbd.CinemaBookings.repository.MovieRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @InjectMocks
    MovieServiceImpl movieService;

    @Mock
    MovieRepository movieRepository;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void findAllMovies() {
        List<Movie> movies = new ArrayList<>();
        Long id = 1L;
        Info info = Info.builder()
                .id(1L)
                .description("New description")
                .director("John")
                .rating(8D)
                .build();
        Movie movie = Movie.builder()
                .id(id)
                .title("Gone with the wind")
                .duration(180L)
                .genre(Genre.DRAMA)
                .movieDetails(info)
                .build();
        movies.add(movie);
        when(movieRepository.findAll()).thenReturn(movies);
        List<Movie> moviesFound = movieService.findAll();
        assertNotNull(moviesFound);
        assertEquals(1, movies.size());
        verify(movieRepository, times(1)).findAll();
    }

    @Test
    void deleteMovieHappyFlow() {
        Long id = 1L;
        doNothing().when(movieRepository).deleteById(id);
        movieService.deleteById(id);
        verify(movieRepository, times(1)).deleteById(id);
    }
}
