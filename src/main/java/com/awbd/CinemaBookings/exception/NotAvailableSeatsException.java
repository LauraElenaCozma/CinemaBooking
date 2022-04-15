package com.awbd.CinemaBookings.exception;

public class NotAvailableSeatsException extends RuntimeException {
    public NotAvailableSeatsException() {
        super("There are no available seats at the reserved event!");
    }
}
