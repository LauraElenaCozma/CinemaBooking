package com.awbd.CinemaBookings.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends NotFoundException {
    public MovieNotFoundException(Long id) {
        super("Movie with id " + id + " was not found");
    }

    public MovieNotFoundException() {
        super("The movie of this event was not found");
    }
}
