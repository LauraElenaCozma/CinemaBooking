package com.awbd.CinemaBookings.domain;

import com.awbd.CinemaBookings.domain.security.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private Date bookingDate;

    @ManyToOne
    private MovieShowing movieShowing;

    @ManyToOne
    private User user;
}
