package com.awbd.CinemaBookings.service;

import com.awbd.CinemaBookings.domain.Actor;
import com.awbd.CinemaBookings.domain.MovieShowing;
import com.awbd.CinemaBookings.exception.ActorNotFoundException;
import com.awbd.CinemaBookings.repository.ActorRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ActorServiceTest {

    @InjectMocks
    private ActorServiceImpl actorService;
    @Mock
    private ActorRepository actorRepository;

    @Test
    void createActorHappyFlow() {
        Actor actor = Actor.builder()
                .firstName("First name")
                .lastName("Last name")
                .build();
        when(actorRepository.save(actor)).thenReturn(actor);

        Actor result = actorService.save(actor);

        assertEquals(actor.getFirstName(), result.getFirstName());
        assertEquals(actor.getLastName(), result.getLastName());
    }

    @Test
    void getAllActorsHappyFlow() {
        Actor actor1 = Actor.builder()
                .id(1L)
                .firstName("First name 1")
                .lastName("Last name 1")
                .build();
        Actor actor2 = Actor.builder()
                .id(2L)
                .firstName("First name 2")
                .lastName("Last name 2")
                .build();
        when(actorRepository.findAll()).thenReturn(Arrays.asList(actor1, actor2));

        List<Actor> result = actorService.findAll();

        assertEquals(actor1.getFirstName(), result.get(0).getFirstName());
        assertEquals(actor1.getLastName(), result.get(0).getLastName());
        assertEquals(actor2.getFirstName(), result.get(1).getFirstName());
        assertEquals(actor2.getLastName(), result.get(1).getLastName());
    }

    @Test
    void getActorHappyFlow() {
        Long id = 1L;
        Actor actor = Actor.builder()
                .id(id)
                .firstName("First name")
                .lastName("Last name")
                .build();
        when(actorRepository.findById(id)).thenReturn(Optional.of(actor));
        Actor result = actorService.findById(id);
        assertNotNull(result);
        assertEquals(actor.getFirstName(), result.getFirstName());
    }

    @Test
    void getActorNegativeFlow() {
        Long id = 1L;
        String expected = "Actor with id " + id + " was not found";
        ActorNotFoundException result = assertThrows(ActorNotFoundException.class,
                () -> actorService.findById(id));
        assertEquals(expected, result.getMessage());
    }

    @Test
    void deleteActorHappyFlow() {
        Long id = 1L;
        doNothing().when(actorRepository).deleteById(id);
        actorService.deleteById(id);
        verify(actorRepository, times(1)).deleteById(id);
    }


    @Test
    void updateActorNegativeFlow() {
        Long id = 1L;
        Actor actor = Actor.builder()
                .firstName("First")
                .lastName("Last")
                .build();
        when(actorRepository.findById(id)).thenReturn(Optional.empty());
        try {
            actorService.update(id, actor);
        } catch (ActorNotFoundException e) {
            assertEquals("Actor with id " + id + " was not found", e.getMessage());
            verify(actorRepository, times(0)).save(actor);
        }
    }



}
