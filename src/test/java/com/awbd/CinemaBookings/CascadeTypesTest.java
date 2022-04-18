package com.awbd.CinemaBookings;

import com.awbd.CinemaBookings.domain.Genre;
import com.awbd.CinemaBookings.domain.Info;
import com.awbd.CinemaBookings.domain.Movie;
import com.awbd.CinemaBookings.domain.MovieShowing;
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
import java.util.Arrays;

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

    // One to many cascade type
//    @Test
//    @Order(4)
//    public

}
