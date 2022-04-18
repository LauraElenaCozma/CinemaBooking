package com.awbd.CinemaBookings.repository;

import com.awbd.CinemaBookings.domain.Genre;
import com.awbd.CinemaBookings.domain.Info;
import com.awbd.CinemaBookings.domain.Movie;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("h2")
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    @Order(1)
    public void addMovies() {
        log.info("Add movies test");
        Movie movie = Movie.builder()
                .title("The King")
                .duration(150L)
                .genre(Genre.DRAMA)
                .build();
        movieRepository.save(movie);
    }

    @Test
    @Order(2)
    public void findByName() {
        log.info("Find by name test");
        List<Movie> movies = movieRepository.findAllByTitle("The King");
        assertFalse(movies.isEmpty());
        log.info("find by movie title...");
        movies.forEach(movie -> log.info(movie.getTitle()));
    }

    @Test
    @Order(3)
    public void findByIds() {
        log.info("Find by ids test");
        List<Movie> movies = movieRepository.findAllByIdIn(Arrays.asList(1L, 2L, 3L));
        assertFalse(movies.isEmpty());
        log.info("Movies with ids 1, 2 and 3...");
        movies.forEach(movie -> log.info(movie.getTitle()));
    }

    @Test
    @Order(4)
    public void findAll() {
        log.info("Find all movies test");
        List<Movie> movies = new LinkedList<>();
        movieRepository.findAll().iterator().forEachRemaining(movies::add);
        assertFalse(movies.isEmpty());
        log.info("The movies are...");
        movies.forEach(movie -> log.info(movie.getTitle()));
    }

    @Test
    @Order(5)
    public void deleteMovie() {
        log.info("delete test");
        List<Movie> movies = movieRepository.findAllByTitle("The King");
        assertFalse(movies.isEmpty());
        Long id = movies.get(0).getId();
        movieRepository.deleteById(id);
        Optional<Movie> movie = movieRepository.findById(id);
        assertTrue(movie.isEmpty());
    }
}
