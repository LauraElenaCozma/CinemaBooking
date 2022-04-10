package com.awbd.CinemaBookings.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private java.util.Date dateOfBirth;

    @ToString.Exclude
    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;
}
