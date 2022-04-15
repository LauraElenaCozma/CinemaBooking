package com.awbd.CinemaBookings.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieShowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The price must not be null")
    @Min(value = 0, message = "The price must have a positive value")
    private Double price;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @NotNull(message = "The date must not be null")
    private java.util.Date date;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(iso=DateTimeFormat.ISO.TIME, pattern = "HH:mm")
    @NotNull(message = "The hour must not be null")
    private java.util.Date hour;

    @ManyToOne
    @NotNull(message = "The movie must be selected")
    private Movie movie;

    @ManyToOne
    @NotNull(message = "The venue must be selected")
    private Venue venue;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "movieShowing")
    private List<Booking> bookings;
}
