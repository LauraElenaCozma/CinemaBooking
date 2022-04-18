package com.awbd.CinemaBookings.controller;

import com.awbd.CinemaBookings.domain.Actor;
import com.awbd.CinemaBookings.domain.Info;
import com.awbd.CinemaBookings.domain.Movie;
import com.awbd.CinemaBookings.domain.MovieShowing;
import com.awbd.CinemaBookings.domain.security.User;
import com.awbd.CinemaBookings.dto.ActorFound;
import com.awbd.CinemaBookings.service.ActorService;
import com.awbd.CinemaBookings.service.InfoService;
import com.awbd.CinemaBookings.service.MovieService;
import com.awbd.CinemaBookings.service.MovieShowingService;
import com.awbd.CinemaBookings.service.security.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class MovieController {

    @Autowired
    MovieService movieService;

    @Autowired
    InfoService infoService;

    @Autowired
    ActorService actorService;

    @Autowired
    MovieShowingService movieShowingService;


    @RequestMapping("/movies/new")
    public String createMovie(Model model) {
        log.info("Create movie get method");
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
        log.info("Create movie post method");
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

    @RequestMapping({"", "/", "/index", "/movies"})
    public ModelAndView getAllMovies() {
        log.info("Get all movies");

        ModelAndView modelAndView = new ModelAndView("movies");
        List<Movie> movies = movieService.findAll();
        modelAndView.addObject("movies", movies);
        return modelAndView;
    }

    @GetMapping("/movies/{id}")
    public String getMovie(@PathVariable String id, Model model,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size) {
        log.info("Get movie by id");

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Movie movie = movieService.findById(Long.valueOf(id));
        Page<MovieShowing> showingsPage = movieShowingService.findAllPagesByMovieId(Long.valueOf(id), PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("showingsPage", showingsPage);
        model.addAttribute("movie", movie);
        model.addAttribute("movieId", id);
        model.addAttribute("info", movie.getMovieDetails());
        return "movieinfo";
    }

    @RequestMapping("/movies/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        log.info("Delete movie");
        movieService.deleteById(id);
        return "redirect:/index";
    }

    @RequestMapping(value = "/movies/update/{id}", method = RequestMethod.GET)
    public String updateMovie(@PathVariable String id, Model model) {
        log.info("Update movie");
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
