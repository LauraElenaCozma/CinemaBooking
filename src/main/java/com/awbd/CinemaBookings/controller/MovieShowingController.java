package com.awbd.CinemaBookings.controller;

import com.awbd.CinemaBookings.domain.MovieShowing;
import com.awbd.CinemaBookings.service.MovieService;
import com.awbd.CinemaBookings.service.MovieShowingService;
import com.awbd.CinemaBookings.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/showings")
public class MovieShowingController {

    @Autowired
    MovieShowingService movieShowingService;

    @Autowired
    VenueService venueService;

    @Autowired
    MovieService movieService;

    @RequestMapping("/new")
    public String createMovieShowing(Model model) {
        model.addAttribute("title", "Create new movie showing");
        model.addAttribute("movieShowing", new MovieShowing());
        model.addAttribute("allMovies", movieService.findAll());
        model.addAttribute("allVenues", venueService.findAll());
        return "showingform";
    }

    @PostMapping
    public String saveOrUpdateMovieShowing(@ModelAttribute MovieShowing movieShowing) {
        MovieShowing savedMovieShowing = movieShowingService.save(movieShowing);
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
        model.addAttribute("movieShowing", movieShowing);
        model.addAttribute("allMovies", movieService.findAll());
        model.addAttribute("allVenues", venueService.findAll());
        return "showingform";
    }
}
