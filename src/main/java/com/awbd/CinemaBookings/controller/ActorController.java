package com.awbd.CinemaBookings.controller;

import com.awbd.CinemaBookings.domain.Actor;
import com.awbd.CinemaBookings.domain.MovieShowing;
import com.awbd.CinemaBookings.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    ActorService actorService;

    @RequestMapping("/new")
    public String createActor(Model model) {
        model.addAttribute("actor", new Actor());
        model.addAttribute("title", "Create new actor");
        return "actorform";
    }

    @PostMapping
    public String saveOrUpdateActor(@ModelAttribute Actor actor) {
        Actor savedActor = actorService.save(actor);
        return "redirect:/actors";
    }

    @GetMapping
    public ModelAndView getAllActors() {
        ModelAndView modelAndView = new ModelAndView("actors");
        List<Actor> actors = actorService.findAll();
        modelAndView.addObject("actors", actors);
        return modelAndView;
    }

    @GetMapping("/{id}")
    public String getActor(@PathVariable String id, Model model) {
        model.addAttribute("actor", actorService.findById(Long.valueOf(id)));
        return "actorinfo";
    }

    @RequestMapping("/delete/{id}")
    public String deleteActor(@PathVariable Long id) {
        actorService.deleteById(id);
        return "redirect:/actors";
    }

    @RequestMapping("/update/{id}")
    public String updateActor(@PathVariable String id, Model model) {
        Actor actor = actorService.findById(Long.valueOf(id));
        model.addAttribute("title", "Update actor info");
        model.addAttribute("actor", actor);
        return "actorform";
    }
}
