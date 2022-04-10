package com.awbd.CinemaBookings.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numReservedSeats;

    private Long movieDate;

    @ManyToOne
    private MovieShowing movieShowing;

    @ManyToOne
    private Customer customer;
}
