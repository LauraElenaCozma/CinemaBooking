package com.awbd.CinemaBookings.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.RequestEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class ActorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "customer", password = "1234", roles = "CUSTOMER")
    void showByIdMvc() throws Exception {
        mockMvc.perform(get("/actors/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("actorinfo"));
    }
}
