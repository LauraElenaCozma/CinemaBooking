package com.awbd.CinemaBookings.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ActorNotFoundException extends NotFoundException{
    public ActorNotFoundException(Long id) {
        super("Actor with id " + id + " was not found");
    }
}
