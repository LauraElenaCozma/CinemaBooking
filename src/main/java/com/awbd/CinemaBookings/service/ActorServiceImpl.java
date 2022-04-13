package com.awbd.CinemaBookings.service;

import com.awbd.CinemaBookings.domain.Actor;
import com.awbd.CinemaBookings.exception.ActorNotFoundException;
import com.awbd.CinemaBookings.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService{
    ActorRepository actorRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public List<Actor> findAll() {
        List<Actor> actors = new LinkedList<>();
        actorRepository.findAll().iterator().forEachRemaining(actors::add);
        return actors;
    }

    @Override
    public Actor findById(Long id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new ActorNotFoundException(id));
    }

    @Override
    public Actor save(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public Actor update(Long id, Actor updatedActor) {
        Optional<Actor> actor = actorRepository.findById(id);
        if(actor.isEmpty())
            throw new ActorNotFoundException(id);
        Actor newActor = actor.get();
        newActor.setFirstName(updatedActor.getFirstName());
        newActor.setLastName(updatedActor.getLastName());
        newActor.setDateOfBirth(updatedActor.getDateOfBirth());
        newActor.setMovies(updatedActor.getMovies());
        //TODO: maybe update movies?
        return actorRepository.save(newActor);
    }


    @Override
    public void deleteById(Long id) {
        actorRepository.deleteById(id);
    }
}
