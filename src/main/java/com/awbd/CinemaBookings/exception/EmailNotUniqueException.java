package com.awbd.CinemaBookings.exception;

public class EmailNotUniqueException extends RuntimeException {
    public EmailNotUniqueException(String email) {
        super("The email " + email + " already exists in the database!");
    }
}
