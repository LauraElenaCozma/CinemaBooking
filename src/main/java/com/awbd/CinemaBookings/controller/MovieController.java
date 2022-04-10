package com.awbd.CinemaBookings.controller;

import com.awbd.CinemaBookings.domain.Actor;
import com.awbd.CinemaBookings.domain.Info;
import com.awbd.CinemaBookings.domain.Movie;
import com.awbd.CinemaBookings.dto.ActorFound;
import com.awbd.CinemaBookings.service.ActorService;
import com.awbd.CinemaBookings.service.InfoService;
import com.awbd.CinemaBookings.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    InfoService infoService;

    @Autowired
    ActorService actorService;

    @RequestMapping("/new")
    public String createMovie(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("info", new Info());
        model.addAttribute("actorsAll", actorService.findAll());
        return "movienew";
    }

    @PostMapping
    public String saveOrUpdateMovie(@ModelAttribute("movie") Movie movie, @ModelAttribute("info") Info info) {
        movie.setMovieDetails(info);
        info.setMovie(movie);
        movieService.save(movie);
        infoService.save(info);
        return "redirect:/movies";
    }

    @GetMapping
    public ModelAndView getAllMovies() {
        ModelAndView modelAndView = new ModelAndView("movies");
        List<Movie> movies = movieService.findAll();
        modelAndView.addObject("movies", movies);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public String getMovie(@PathVariable String id, Model model) {
        Movie movie = movieService.findById(Long.valueOf(id));
        model.addAttribute("movie", movie);
        model.addAttribute("info", movie.getMovieDetails());
        return "movieinfo";
    }

    @RequestMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.deleteById(id);
        return "redirect:/movies";
    }

    @RequestMapping("/update/{id}")
    public String updateMovie(@PathVariable String id, Model model) {
        Movie movie = movieService.findById(Long.valueOf(id));

        List<Actor> movieActors = movie.getActors();
        List<Actor> allActors = actorService.findAll();
        List<ActorFound> selectedActors = allActors.stream()
                .map(actor -> {
                    if (movieActors.contains(actor))
                        return new ActorFound(actor, true);
                    return new ActorFound(actor, false);
                })
                .collect(Collectors.toList());

        model.addAttribute("movie", movie);
        model.addAttribute("info", movie.getMovieDetails());
        model.addAttribute("selectedActors", selectedActors);
        return "movieupdate";
    }
}
