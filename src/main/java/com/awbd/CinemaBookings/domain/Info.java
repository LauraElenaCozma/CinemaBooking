package com.awbd.CinemaBookings.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String director;

    private Double rating;

    @ToString.Exclude
    @OneToOne
    private Movie movie;
}
