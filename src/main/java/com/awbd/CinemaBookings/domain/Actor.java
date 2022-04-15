package com.awbd.CinemaBookings.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "First name must not be empty!")
    @Size(max = 50, message = "First name must not be longer than 50 characters")
    private String firstName;

    @NotEmpty(message = "Last name must not be empty!")
    @Size(max = 50, message = "Last name must not be longer than 50 characters")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    @NotNull(message = "Date of birth must not be empty!")
    private java.util.Date dateOfBirth;

    @Size(max = 50, message = "Place of birth must not be longer than 50 characters")
    private String placeOfBirth;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "actors")
    private List<Movie> movies;
}
