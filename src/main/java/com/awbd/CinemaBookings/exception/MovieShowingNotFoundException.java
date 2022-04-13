package com.awbd.CinemaBookings.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieShowingNotFoundException extends NotFoundException{
    public MovieShowingNotFoundException(Long id) {
        super("Movie showing with id " + id + " was not found");
    }
}
