package com.awbd.CinemaBookings.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InfoNotFoundException extends NotFoundException{
    public InfoNotFoundException(Long id) {
        super("Info with id " + id + " was not found");
    }
}
