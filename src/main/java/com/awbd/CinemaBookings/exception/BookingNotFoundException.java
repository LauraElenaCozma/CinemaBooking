package com.awbd.CinemaBookings.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookingNotFoundException extends NotFoundException{
    public BookingNotFoundException(Long id) {
        super("Booking with id " + id + " was not found");
    }
}
