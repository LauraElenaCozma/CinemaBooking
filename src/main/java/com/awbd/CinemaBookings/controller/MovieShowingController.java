package com.awbd.CinemaBookings.controller;

import com.awbd.CinemaBookings.domain.Movie;
import com.awbd.CinemaBookings.domain.MovieShowing;
import com.awbd.CinemaBookings.domain.Venue;
import com.awbd.CinemaBookings.service.MovieService;
import com.awbd.CinemaBookings.service.MovieShowingService;
import com.awbd.CinemaBookings.service.VenueService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/showings")
@Log
public class MovieShowingController {

    private static final Logger log = Logger.getLogger(MovieShowingController.class.getName());

    @Autowired
    MovieShowingService movieShowingService;

    @Autowired
    VenueService venueService;

    @Autowired
    MovieService movieService;

    @RequestMapping("/new")
    public String createMovieShowing(Model model) {
        model.addAttribute("title", "Create new movie showing");
        log.log(Level.INFO, "Create init");
        if(!model.containsAttribute("movieShowing"))
            model.addAttribute("movieShowing", new MovieShowing());
        log.log(Level.INFO, "After movie showing");
        model.addAttribute("allMovies", movieService.findAll());
        log.log(Level.INFO, "After all movies");
        model.addAttribute("allVenues", venueService.findAll());
        log.log(Level.INFO, "All venues added");
        return "showingform";
    }

    @PostMapping
    public String saveOrUpdateMovieShowing(@Valid @ModelAttribute("movieShowing") MovieShowing movieShowing, BindingResult bindingResult,
                                           RedirectAttributes attr) {
        if (bindingResult.hasErrors()) {
            log.log(Level.INFO, "Binding result has errors");
            attr.addFlashAttribute("org.springframework.validation.BindingResult.movieShowing", bindingResult);
            attr.addFlashAttribute("movieShowing", movieShowing);
            if (movieShowing.getId() != null)
                return "redirect:/showings/update/" + movieShowing.getId().toString();
            return "redirect:/showings/new";
        }
        log.log(Level.INFO, "Binding result doesn't have errors");
        movieShowingService.save(movieShowing);
        return "redirect:/showings";
    }

    @GetMapping
    public ModelAndView getAllMovieShowings() {
        ModelAndView modelAndView = new ModelAndView("showings");
        List<MovieShowing> movieShowings = movieShowingService.findAll();
        modelAndView.addObject("movieShowings", movieShowings);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public String getMovieShowing(@PathVariable String id, Model model) {
        model.addAttribute("showing", movieShowingService.findById(Long.valueOf(id)));
        return "showinginfo";
    }

    @RequestMapping("/delete/{id}")
    public String deleteMovieShowing(@PathVariable Long id) {
        movieShowingService.deleteById(id);
        return "redirect:/showings";
    }

    @RequestMapping("/update/{id}")
    public String updateMovieShowing(@PathVariable String id, Model model) {
        MovieShowing movieShowing = movieShowingService.findById(Long.valueOf(id));
        model.addAttribute("title", "Update movie showing");
        if(!model.containsAttribute("movieShowing"))
            model.addAttribute("movieShowing", movieShowing);
        model.addAttribute("allMovies", movieService.findAll());
        model.addAttribute("allVenues", venueService.findAll());
        return "showingform";
    }
}
