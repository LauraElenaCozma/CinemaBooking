package com.awbd.CinemaBookings.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "You must reserve a number of seats!")
    @Min(value = 1, message = "Minimum 1 seat reserved")
    @Max(value = 20, message = "Maximum 20 seats reserved")
    private Integer numReservedSeats;

    private Long bookingDate;

    @ManyToOne
    private MovieShowing movieShowing;

    @ManyToOne
    private Customer customer;
}
