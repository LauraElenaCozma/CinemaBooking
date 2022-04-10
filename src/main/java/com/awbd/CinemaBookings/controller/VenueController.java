package com.awbd.CinemaBookings.controller;

import com.awbd.CinemaBookings.domain.Actor;
import com.awbd.CinemaBookings.domain.Venue;
import com.awbd.CinemaBookings.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/venues")
public class VenueController {

    @Autowired
    VenueService venueService;

    @RequestMapping("/new")
    public String createVenue(Model model) {
        model.addAttribute("venue", new Venue());
        model.addAttribute("title", "Create new venue");
        return "venueform";
    }

    @PostMapping
    public String saveOrUpdateVenue(@ModelAttribute Venue venue) {
        Venue savedVenue = venueService.save(venue);
        return "redirect:/venues";
    }

    @GetMapping
    public ModelAndView getAllVenues() {
        ModelAndView modelAndView = new ModelAndView("venues");
        List<Venue> venues = venueService.findAll();
        modelAndView.addObject("venues", venues);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public String getVenue(@PathVariable String id, Model model) {
        model.addAttribute("venue", venueService.findById(Long.valueOf(id)));
        return "venueinfo";
    }

    @RequestMapping("/delete/{id}")
    public String deleteVenue(@PathVariable Long id) {
        venueService.deleteById(id);
        return "redirect:/venues";
    }

    @RequestMapping("/update/{id}")
    public String updateVenue(@PathVariable String id, Model model) {
        Venue venue = venueService.findById(Long.valueOf(id));
        model.addAttribute("title", "Update venue info");
        model.addAttribute("venue", venue);
        return "venueform";
    }
}
