package com.awbd.CinemaBookings.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class MovieShowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private java.util.Date date;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(iso=DateTimeFormat.ISO.TIME, pattern = "HH:mm")
    private java.util.Date hour;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private Venue venue;

    @ToString.Exclude
    @OneToMany(mappedBy = "movieShowing")
    private List<Booking> bookings;
}
