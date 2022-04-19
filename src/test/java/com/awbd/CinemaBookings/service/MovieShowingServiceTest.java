package com.awbd.CinemaBookings.service;

import com.awbd.CinemaBookings.domain.MovieShowing;
import com.awbd.CinemaBookings.exception.MovieShowingNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("h2")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class MovieShowingServiceTest {

    @Autowired
    MovieShowingServiceImpl movieShowingService;

    @Test
    @Order(1)
    public void getById() {
        log.info("Get by id movie showing...");
        Long id = 1L;
        MovieShowing movieShowing = movieShowingService.findById(id);
        assertNotNull(movieShowing);
        assertEquals(40D, movieShowing.getPrice());
    }

    @Test
    @Order(2)
    public void updateMovieShowing() {
        log.info("Update movie showing...");
        Long id = 1L;
        MovieShowing movieShowing = movieShowingService.findById(id);
        movieShowing.setPrice(100D);
        movieShowingService.save(movieShowing);
        MovieShowing newMovieShowing = movieShowingService.findById(id);
        assertEquals(100D, newMovieShowing.getPrice());
    }

    @Test
    @Order(3)
    public void deleteById() {
        log.info("Delete movie showing test... ");
        Long id = 1L;
        movieShowingService.deleteById(id);
        assertThrows(MovieShowingNotFoundException.class, () -> movieShowingService.findById(id));
    }
}
