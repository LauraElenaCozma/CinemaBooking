package com.awbd.CinemaBookings;

import com.awbd.CinemaBookings.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(false)
@Slf4j
public class CascadeTypesTest {
    @Autowired
    private EntityManager entityManager;

    // One to one cascade type

    @Test
    @Order(1)
    public void saveMovie() {
        log.info("save movie test ...");
        log.info("cascade type persist");
        Info info = Info.builder()
                .description("This is a description")
                .rating(10D)
                .director("Director Name")
                .build();
        Movie movie = Movie.builder()
                .title("New movie")
                .duration(100L)
                .genre(Genre.BIOGRAPHY)
                .movieDetails(info)
                .build();
        info.setMovie(movie);
        log.info("save movie (parent) -> info is also saved ...");
        entityManager.persist(movie);
        entityManager.flush();
        entityManager.clear();
    }


    @Test
    @Order(2)
    public void updateMovie() {
        log.info("Update movie test");
        log.info("Cascade type merge");
        Movie movie = entityManager.find(Movie.class, 7L);
        Info info = movie.getMovieDetails();
        movie.setGenre(Genre.DRAMA);
        info.setRating(9D);
        info.setDescription("This is a new description");
        log.info("update movie automatically updates info");
        entityManager.merge(movie);
        entityManager.flush();

        Movie savedMovie = entityManager.find(Movie.class, 7L);
        assertEquals(Genre.DRAMA, savedMovie.getGenre());
        assertNotNull(savedMovie.getMovieDetails());
        assertEquals(9D, movie.getMovieDetails().getRating());
    }

    @Test
    @Order(3)
    public void removeMovie() {
        log.info("Remove movie test");
        log.info("Cascade type remove");
        Movie movie =entityManager.find(Movie.class, 7L);
        Info info = movie.getMovieDetails();
        assertNotNull(info);
        Long id = info.getId();

        entityManager.remove(movie);
        entityManager.flush();
        Info deletedInfo = entityManager.find(Info.class, id);
        assertNull(deletedInfo);
    }

//    One to many cascade type
    @Test
    @Order(4)
    public void addMovieShowings() {
        Date hour1 = null;
        try {
            hour1 = new SimpleDateFormat("HH:mm").parse("20:00");
        } catch (ParseException e) {
            log.error("Parsing hour error");
        }
        Date hour2 = null;
        try {
            hour2 = new SimpleDateFormat("HH:mm").parse("20:30");
        } catch (ParseException e) {
            log.error("Parsing hour error");
        }
        log.info("Add movie showings test");
        Movie movie = Movie.builder()
                .title("New movie")
                .duration(100L)
                .genre(Genre.BIOGRAPHY)
                .build();
        Venue venue = Venue.builder()
               .venueName("Sala 1")
               .locationName("Location")
               .seatCapacity(100)
               .build();
        MovieShowing movieShowing1 = MovieShowing.builder()
                .movie(movie)
                .date(new Date())
                .hour(hour1)
                .price(100D)
                .venue(venue)
                .build();
        MovieShowing movieShowing2 = MovieShowing.builder()
                .movie(movie)
                .date(new Date())
                .hour(hour2)
                .price(50D)
                .venue(venue)
                .build();

        entityManager.persist(venue);
        movie.setMovieShowings(Arrays.asList(movieShowing1, movieShowing2));
        log.info("Adding movie showings...");
        entityManager.persist(movie);
        entityManager.flush();
        entityManager.clear();
    }

    @Test
    @Order(5)
    public void updateMovieKeepsMovieShowing() {
        log.info("Update movie - movie showings test");
        log.info("Cascade type merge");
        Movie movie = entityManager.find(Movie.class, 8L);
        List<Long> ids = movie.getMovieShowings()
                .stream()
                .map(movieShowing -> movieShowing.getId())
                .collect(Collectors.toList());
        assertEquals(2, ids.size());
        movie.setGenre(Genre.DRAMA);
        log.info("update movie keeps movie showing");
        entityManager.merge(movie);
        entityManager.flush();

        Movie savedMovie = entityManager.find(Movie.class, 8L);
        assertEquals(Genre.DRAMA, savedMovie.getGenre());

        for(Long id : ids) {
            MovieShowing movieShowing = entityManager.find(MovieShowing.class, id);
            assertNotNull(movieShowing);
        }
    }

    @Test
    @Order(6)
    public void deleteMovieDeletesMovieShowings() {
        log.info("Delete movie showings test");
        log.info("Cascade type remove");
        Movie movie = entityManager.find(Movie.class, 8L);
        List<Long> ids = movie.getMovieShowings()
                .stream()
                .map(movieShowing -> movieShowing.getId())
                .collect(Collectors.toList());
        assertEquals(2, ids.size());
        entityManager.remove(movie);
        entityManager.flush();

        for(Long id : ids) {
            MovieShowing movieShowing = entityManager.find(MovieShowing.class, id);
            assertNull(movieShowing);
        }
    }

}
