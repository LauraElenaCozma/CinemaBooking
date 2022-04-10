package com.awbd.CinemaBookings.service;

import com.awbd.CinemaBookings.domain.Actor;

import java.util.List;

public interface ActorService {
    List<Actor> findAll();
    Actor findById(Long id);
    Actor save(Actor actor);
    Actor update(Long id, Actor updatedActor);
    void deleteById(Long id);

}
