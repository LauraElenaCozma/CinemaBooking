package com.awbd.CinemaBookings.controller;

import com.awbd.CinemaBookings.domain.MovieShowing;
import com.awbd.CinemaBookings.service.MovieShowingService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class MovieShowingControllerTest {

    @Mock
    Model model;

    @Mock
    MovieShowingService movieShowingService;


    MovieShowingController movieShowingController;

    @BeforeEach
    public void setUp() throws Exception {
        movieShowingController = new MovieShowingController();
        movieShowingController.setMovieShowingService(movieShowingService);
    }

    @Test
    void getById() {
        // check if MovieShowingController adds in Model the movieShowing object
        // returned by findById method of movieShowingService
        log.info("Started get by id test");
        Long id = 1L;
        MovieShowing movieShowing = MovieShowing.builder().id(id).build();
        when(movieShowingService.findById(id)).thenReturn(movieShowing);

        String viewName = movieShowingController.getMovieShowing(id.toString(),
                model);
        assertEquals("showinginfo", viewName);
        verify(movieShowingService, times(1)).findById(id);
        ArgumentCaptor<MovieShowing> argumentCaptor = ArgumentCaptor.forClass(MovieShowing.class);
        verify(model, times(1)).addAttribute(eq("showing"), argumentCaptor.capture());
        MovieShowing movieShowingArg = argumentCaptor.getValue();
        assertEquals(movieShowingArg.getId(), movieShowing.getId());
        log.info("End of test");
    }
}
