package com.awbd.CinemaBookings.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
@Entity
@Data
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The venue name must not be null")
    @Size(max = 50, message = "The name of the venue must not be longer than 50 characters")
    private String venueName;

    @NotEmpty(message = "The location must not be null")
    @Size(max = 50, message = "The name of the location must not be longer than 50 characters")
    private String locationName;

    @NotNull(message = "The capacity must not be null")
    @Min(value = 10, message = "The capacity must be greater than 10")
    private Integer seatCapacity;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL)
    private List<MovieShowing> movieShowings;
}
