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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    InfoService infoService;

    @Autowired
    ActorService actorService;

    @RequestMapping("/movies/new")
    public String createMovie(Model model) {
        if(!model.containsAttribute("movie"))
            model.addAttribute("movie", new Movie());
        if(!model.containsAttribute("info"))
            model.addAttribute("info", new Info());
        model.addAttribute("actorsAll", actorService.findAll());
        return "movienew";
    }

    @PostMapping("/movies")
    public String saveOrUpdateMovie(@Valid @ModelAttribute("movie") Movie movie, BindingResult bindingMovie,
                                    @Valid @ModelAttribute("info") Info info, BindingResult bindingInfo,
                                    RedirectAttributes attr) {
        if(bindingMovie.hasErrors() || bindingInfo.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.movie", bindingMovie);
            attr.addFlashAttribute("movie", movie);
            attr.addFlashAttribute("org.springframework.validation.BindingResult.info", bindingInfo);
            attr.addFlashAttribute("info", info);
            if(movie.getId() != null)
                return "redirect:/movies/update/" + movie.getId().toString();
            return "redirect:/movies/new";
        }
        movie.setMovieDetails(info);
        info.setMovie(movie);
        movieService.save(movie);
        infoService.save(info);
        return "redirect:/index";
    }

    @RequestMapping({"", "/", "/index"})
    public ModelAndView getAllMovies() {
        ModelAndView modelAndView = new ModelAndView("movies");
        List<Movie> movies = movieService.findAll();
        modelAndView.addObject("movies", movies);
        return modelAndView;
    }

    @GetMapping("/movies/{id}")
    public String getMovie(@PathVariable String id, Model model) {
        Movie movie = movieService.findById(Long.valueOf(id));
        model.addAttribute("movie", movie);
        model.addAttribute("info", movie.getMovieDetails());
        return "movieinfo";
    }

    @RequestMapping("/movies/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.deleteById(id);
        return "redirect:/index";
    }

    @RequestMapping("/movies/update/{id}")
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

        if(!model.containsAttribute("movie"))
            model.addAttribute("movie", movie);
        if(!model.containsAttribute("info"))
            model.addAttribute("info", movie.getMovieDetails());
        model.addAttribute("selectedActors", selectedActors);
        return "movieupdate";
    }
}
