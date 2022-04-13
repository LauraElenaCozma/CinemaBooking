package com.awbd.CinemaBookings.controller;

import com.awbd.CinemaBookings.domain.Actor;
import com.awbd.CinemaBookings.domain.MovieShowing;
import com.awbd.CinemaBookings.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    ActorService actorService;

    @RequestMapping("/new")
    public String createActor(Model model) {
        if(!model.containsAttribute("actor"))
            model.addAttribute("actor", new Actor());
        return "actorform";
    }

    @PostMapping
    public String saveOrUpdateActor(@Valid @ModelAttribute("actor") Actor actor, BindingResult bindingResult,
                                    RedirectAttributes attr) {
        if (bindingResult.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.actor", bindingResult);
            attr.addFlashAttribute("actor", actor);
            if(actor.getId() != null)
                return "redirect:/actors/update/" + actor.getId().toString();
            return "redirect:/actors/new";
        }
        actorService.save(actor);
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
        if(!model.containsAttribute("actor"))
            model.addAttribute("actor", actor);
        return "actorform";
    }
}
