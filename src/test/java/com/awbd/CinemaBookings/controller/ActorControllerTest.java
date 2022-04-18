package com.awbd.CinemaBookings.controller;

import com.awbd.CinemaBookings.domain.Actor;
import com.awbd.CinemaBookings.domain.Movie;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.RequestEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class ActorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "customer", password = "1234", roles = "CUSTOMER")
    void showByIdSuccess() throws Exception {
        mockMvc.perform(get("/actors/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("actorinfo"))
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    void showByIdNoAuth() throws Exception {
        mockMvc.perform(get("/actors/{id}", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    @WithMockUser(username = "customer", password = "1234", roles = "CUSTOMER")
    void showAllSuccess() throws Exception {
        mockMvc.perform(get("/actors"))
                .andExpect(status().isOk())
                .andExpect(view().name("actors"))
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    void showAllNoAuth() throws Exception {
        mockMvc.perform(get("/actors"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
    void newActorSuccess() throws Exception {
        Actor actor = new Actor();
        mockMvc.perform(get("/actors/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("actorform"))
                .andExpect(model().attribute("actor", actor))
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    @WithMockUser(username = "admin", password = "1234", roles = "ADMIN")
    void addNewActorSuccess() throws Exception {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse("1974-02-12");
        } catch (ParseException e) {
            log.error("Parsing date error");
        }
        Actor actor = Actor.builder()
                .firstName("First")
                .lastName("Last")
                .dateOfBirth(date).build();
        mockMvc.perform(post("/actors")
                .flashAttr("actor", actor))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/actors"));
    }

}
