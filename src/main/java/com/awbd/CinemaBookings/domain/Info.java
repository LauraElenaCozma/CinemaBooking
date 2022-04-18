package com.awbd.CinemaBookings.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The description must be completed")
    @Size(max = 300, message = "The description must not be longer than 300 characters")
    private String description;

    @NotEmpty(message = "The name of the director must be completed")
    @Size(max = 50, message = "The name of the director must not be longer than 50 characters")
    private String director;

    @NotNull(message = "The rating must be completed")
    @Min(value = 1, message = "The rating must be greater than 1")
    @Max(value = 10, message = "The rating must be lower than 10")
    private Double rating;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne
    private Movie movie;
}
