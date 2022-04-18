package com.awbd.CinemaBookings.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // OPTIONAL
    // private Byte[] image;

    @NotEmpty(message = "The title must not be empty!")
    @Size(max = 80, message = "The title of the play must not be longer than 80 characters")
    private String title;

    @NotNull(message = "The duration must not be null!")
    @Min(value = 0, message = "The duration must be a positive value!")
    private Long duration;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL)
    private Info movieDetails;

    @ManyToMany//(fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinTable(name="movie_actor",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="actor_id", referencedColumnName = "id"))
    private List<Actor> actors;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<MovieShowing> movieShowings;

}
