package com.awbd.CinemaBookings.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VenueNotFoundException extends NotFoundException{
    public VenueNotFoundException(Long id) {
        super("Venue with id " + id + " was not found");
    }
}