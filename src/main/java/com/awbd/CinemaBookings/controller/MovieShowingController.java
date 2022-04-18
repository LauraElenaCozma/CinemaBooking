package com.awbd.CinemaBookings.controller;

import com.awbd.CinemaBookings.domain.Movie;
import com.awbd.CinemaBookings.domain.MovieShowing;
import com.awbd.CinemaBookings.domain.Venue;
import com.awbd.CinemaBookings.service.MovieService;
import com.awbd.CinemaBookings.service.MovieShowingService;
import com.awbd.CinemaBookings.service.VenueService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/showings")
@Slf4j
public class MovieShowingController {

    @Autowired
    MovieShowingService movieShowingService;

    @Autowired
    VenueService venueService;

    @Autowired
    MovieService movieService;

    public void setMovieShowingService(MovieShowingService movieShowingService) {
        this.movieShowingService = movieShowingService;
    }

    @RequestMapping("/new")
    public String createMovieShowing(@RequestParam(required = false) Long movieId, Model model) {
        model.addAttribute("title", "Create new movie showing");
        log.info("Create init");
        if(!model.containsAttribute("movieShowing"))
            model.addAttribute("movieShowing", new MovieShowing());
        log.info( "After movie showing");
        model.addAttribute("allMovies", movieService.findAll());
        log.info( "After all movies");
        model.addAttribute("allVenues", venueService.findAll());
        log.info( "All venues added");
        model.addAttribute("movieId", movieId);
        return "showingform";
    }

    @PostMapping
    public String saveOrUpdateMovieShowing(@Valid @ModelAttribute("movieShowing") MovieShowing movieShowing, BindingResult bindingResult,
                                           RedirectAttributes attr) {
        if (bindingResult.hasErrors()) {
            log.info("Binding result has errors");
            attr.addFlashAttribute("org.springframework.validation.BindingResult.movieShowing", bindingResult);
            attr.addFlashAttribute("movieShowing", movieShowing);
            if (movieShowing.getId() != null)
                return "redirect:/showings/update/" + movieShowing.getId().toString();
            return "redirect:/showings/new";
        }
        log.info("Binding result doesn't have errors");
        movieShowingService.save(movieShowing);
        return "redirect:/showings";
    }

//    @GetMapping
//    public ModelAndView getAllMovieShowings() {
//        ModelAndView modelAndView = new ModelAndView("showings");
//        List<MovieShowing> movieShowings = movieShowingService.findAll();
//        modelAndView.addObject("movieShowings", movieShowings);
//        return modelAndView;
//    }

    @GetMapping
    public String getAllMovieShowingsPage(Model model,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size) {
        log.info("Get all movie showings by pages");
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<MovieShowing> showingsPage = movieShowingService.findAllPages(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("showingsPage", showingsPage);
        return "showingspages";
    }

    @GetMapping("/{id}")
    public String getMovieShowing(@PathVariable String id, Model model) {
        log.info("Get movie showing by id");
        model.addAttribute("showing", movieShowingService.findById(Long.valueOf(id)));
        return "showinginfo";
    }

    @RequestMapping("/delete/{id}")
    public String deleteMovieShowing(@PathVariable Long id) {
        log.info("Delete movie showing");
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
