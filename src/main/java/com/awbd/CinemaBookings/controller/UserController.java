package com.awbd.CinemaBookings.controller;

import com.awbd.CinemaBookings.domain.security.User;
import com.awbd.CinemaBookings.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "userform";
    }

    @PostMapping
    public String saveOrUpdateUser(@ModelAttribute User user) {//, BindingResult binding, RedirectAttributes attr) {
//        if(binding.hasErrors()) {
//            attr.addFlashAttribute("org.springframework.validation.BindingResult.user", binding);
//            attr.addFlashAttribute("user", user);
//            if(user.getId() != null)
//                return "redirect:/users/update/" + user.getId().toString();
//            return "redirect:/movies/new";
//        }
        User savedUser = userService.save(user);
        return "redirect:/users";
    }

    @GetMapping
    public ModelAndView getAllUsers() {
        ModelAndView modelAndView = new ModelAndView("Users");
        List<User> users = userService.findAll();
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping("/myProfile")
    public String getMyProfile(Model model) {
        User currentUser = userService.getCurrentUser();
        model.addAttribute("user", currentUser);
        return "userinfo";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable String id, Model model) {
        model.addAttribute("user", userService.findById(Long.valueOf(id)));
        return "userinfo";
    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/users";
    }
}
