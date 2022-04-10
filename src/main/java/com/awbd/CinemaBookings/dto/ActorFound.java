package com.awbd.CinemaBookings.dto;

import com.awbd.CinemaBookings.domain.Actor;
import lombok.Data;


public class ActorFound {
    private Actor actor;
    private Boolean present;

    public ActorFound() {
    }

    public ActorFound(Actor actor, Boolean present) {
        this.actor = actor;
        this.present = present;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }
}
