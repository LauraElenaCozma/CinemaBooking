package com.awbd.CinemaBookings.exception;

public class UsernameNotUniqueException extends RuntimeException {
    public UsernameNotUniqueException(String username) {
        super("The username " + username + " already exists in the database!");
    }
}
