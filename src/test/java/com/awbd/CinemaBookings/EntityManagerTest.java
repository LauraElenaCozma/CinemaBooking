package com.awbd.CinemaBookings;

import com.awbd.CinemaBookings.domain.Genre;
import com.awbd.CinemaBookings.domain.Movie;
import com.awbd.CinemaBookings.domain.MovieShowing;
import com.awbd.CinemaBookings.domain.Venue;
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

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("h2")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(false)
@Slf4j
public class EntityManagerTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    @Order(1)
    public void findMovie() {
        log.info("Find movie test...");
        Movie movieFound = entityManager.find(Movie.class, 1L);
        assertNotNull(movieFound);
        assertEquals("Gone with the wind", movieFound.getTitle());
        assertEquals(Genre.DRAMA, movieFound.getGenre());
        log.info(movieFound.getTitle());
    }

    @Test
    @Order(2)
    public void updateMovie() {
        log.info("Update movie test...");
        Movie movieFound = entityManager.find(Movie.class, 1L);
        movieFound.setGenre(Genre.COMEDY);
        entityManager.persist(movieFound);
        entityManager.flush();
    }

    @Test
    @Order(3)
    public void checkMovieGenre() {
        log.info("Check genre test...");
        Movie movieFound = entityManager.find(Movie.class, 1L);
        assertEquals(movieFound.getGenre(), Genre.COMEDY);
    }

    @Test
    @Order(4)
    public void findMovieShowing() {
        log.info("Find movie showing test...");
        MovieShowing movieShowing = entityManager.find(MovieShowing.class, 1L);
        assertNotNull(movieShowing);
        assertEquals(40D, movieShowing.getPrice());
        assertNotNull(movieShowing.getMovie());
        assertEquals(1L, movieShowing.getMovie().getId());
        assertNotNull(movieShowing.getVenue());
        assertEquals(1L, movieShowing.getVenue().getId());
    }

    @Test
    @Order(5)
    public void updateMovieShowing() {
        log.info("Update movie showing test...");
        MovieShowing movieShowing = entityManager.find(MovieShowing.class, 1L);
        movieShowing.setPrice(50D);
        Venue newVenue = entityManager.find(Venue.class, 2L);
        movieShowing.setVenue(newVenue);
        entityManager.persist(movieShowing);
        entityManager.flush();
    }

    @Test
    @Order(6)
    public void checkShowingPriceAndVenue() {
        log.info("Check update movie showing test...");
        MovieShowing movieShowing = entityManager.find(MovieShowing.class, 1L);
        assertNotNull(movieShowing);
        assertEquals(50D, movieShowing.getPrice());
        assertNotNull(movieShowing.getVenue());
        assertEquals(2L, movieShowing.getVenue().getId());
        assertEquals("Sala 2", movieShowing.getVenue().getVenueName());
    }


}
