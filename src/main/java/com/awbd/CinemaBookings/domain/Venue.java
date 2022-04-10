package com.awbd.CinemaBookings.domain;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
@Entity
@Data
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String venueName;

    private String locationName;

    private Integer seatCapacity;

    @ToString.Exclude
    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    private List<MovieShowing> movieShowings;
}
