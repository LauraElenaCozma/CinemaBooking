package com.awbd.CinemaBookings.service;

import com.awbd.CinemaBookings.domain.MovieShowing;
import com.awbd.CinemaBookings.repository.MovieShowingRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("h2")
@Slf4j
public class MovieShowingServiceTest {

    @Autowired
    MovieShowingServiceImpl movieShowingService;

//    @MockBean
//    MovieShowingRepository movieShowingRepository;

    @Test
    public void deleteById() {
        log.info("Delete movie showing test... ");
        Long id = 1L;
        movieShowingService.deleteById(id);
//        doNothing().when(movieShowingRepository).deleteById(id);
        MovieShowing movieShowing = movieShowingService.findById(id);
        assertNull(movieShowing);
    }
}
