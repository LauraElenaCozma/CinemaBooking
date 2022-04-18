package com.awbd.CinemaBookings.controller;

import com.awbd.CinemaBookings.domain.Genre;
import com.awbd.CinemaBookings.domain.Info;
import com.awbd.CinemaBookings.domain.Movie;
import com.awbd.CinemaBookings.exception.MovieNotFoundException;
import com.awbd.CinemaBookings.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("h2")
public class MovieControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MovieService movieService;

    @MockBean
    Model model;

    @Test
    @WithMockUser(username = "customer", password = "1234", roles = "CUSTOMER")
    void accessMoviesSuccess() throws Exception {
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

        List<Movie> movies = new ArrayList<>();
        movies.add(movie);
        when(movieService.findAll()).thenReturn(movies);
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("movies", movies))
                .andExpect(view().name("movies"))
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    @WithMockUser(username = "customer", password = "1234", roles = "CUSTOMER")
    void accessMovieInfoSuccess() throws Exception {
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

        when(movieService.findById(id)).thenReturn(movie);
        mockMvc.perform(get("/movies/{id}",id.toString()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("movie", movie))
                .andExpect(view().name("movieinfo"))
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    @WithMockUser(username = "customer", password = "1234", roles = "CUSTOMER")
    void accessMovieInfoFailure() throws Exception {
        Long id = 100L;
        when(movieService.findById(id)).thenThrow(new MovieNotFoundException());
        mockMvc.perform(get("/movies/{id}", id.toString()))
                .andExpect(status().isNotFound())
                .andExpect(view().name("notfound"));
    }

    @Test
    @WithMockUser(username = "customer", password = "1234", roles = "CUSTOMER")
    void accessMovieUpdateAccessDenied() throws Exception {
        mockMvc.perform(get("/movies/update/{id}","1"))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/accessDenied"));
    }


    @Test
    @WithMockUser(username = "customer", password = "1234", roles = "CUSTOMER")
    void accessMovieNewAccessDenied() throws Exception {
        mockMvc.perform(get("/movies/new"))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/accessDenied"));
    }

    @Test
    @WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
    void accessMovieNewSuccess() throws Exception {
        mockMvc.perform(get("/movies/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("movienew"));
    }
    

    @Test
    @WithMockUser(username = "customer", password = "1234", roles = "CUSTOMER")
    void accessMovieDeleteAccessDenied() throws Exception {
        mockMvc.perform(get("/movies/delete/{id}", 1))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/accessDenied"));
    }

    @Test
    @WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
    void accessMovieDeleteSuccess() throws Exception {
        Long id = 1L;
        doNothing().when(movieService).deleteById(id);
        mockMvc.perform(get("/movies/delete/{id}", id.toString()))
                .andExpect(redirectedUrl("/index"));
    }
}
