package com.awbd.CinemaBookings.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // OPTIONAL
    // private Byte[] image;

    private String title;

    private Long duration;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @OneToOne(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Info movieDetails;

    @ManyToMany
    @JoinTable(name="movie_actor",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="actor_id", referencedColumnName = "id"))
    private List<Actor> actors;

    @ToString.Exclude
    @OneToMany(mappedBy = "movie")
    private List<MovieShowing> movieShowings;
}
