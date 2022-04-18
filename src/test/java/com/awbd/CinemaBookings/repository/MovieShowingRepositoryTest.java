package com.awbd.CinemaBookings.repository;

import com.awbd.CinemaBookings.domain.MovieShowing;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("h2")
@Slf4j
public class MovieShowingRepositoryTest {
    @Autowired
    private MovieShowingRepository movieShowingRepository;

    @Test
    public void findPage() {
        log.info("Find page test...");
        Pageable firstPage = PageRequest.of(0, 5);
        Page<MovieShowing> allMovieShowings = movieShowingRepository.findAll(firstPage);
        log.info("Number of elements in page: " + allMovieShowings.getNumberOfElements());
        Assert.assertTrue(allMovieShowings.getNumberOfElements() == 5);
    }

    @Test
    public void findPageByMovieId() {
        log.info("Find page by movie id test...");
        Pageable firstPage = PageRequest.of(0, 2);
        Page<MovieShowing> allMovieShowings = movieShowingRepository.findAllByMovie_Id(1L, firstPage);
        log.info("Number of elements in page: " + allMovieShowings.getNumberOfElements());
        Assert.assertTrue(allMovieShowings.getNumberOfElements() == 2);
    }

}
