package com.awbd.CinemaBookings.controller;

import com.awbd.CinemaBookings.domain.MovieShowing;
import com.awbd.CinemaBookings.domain.security.User;
import com.awbd.CinemaBookings.exception.EmailNotUniqueException;
import com.awbd.CinemaBookings.exception.PhoneNotUniqueException;
import com.awbd.CinemaBookings.exception.UsernameNotUniqueException;
import com.awbd.CinemaBookings.service.security.AuthorityService;
import com.awbd.CinemaBookings.service.security.UserService;
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
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private AuthorityService authorityService;

    @RequestMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "userform";
    }

    @PostMapping
    public String saveOrUpdateUser(@Valid @ModelAttribute("user") User user, BindingResult binding, RedirectAttributes attr) {
        if(binding.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.user", binding);
            attr.addFlashAttribute("user", user);
            if(user.getId() != null)
                return "redirect:/users/update/" + user.getId().toString();
            return "redirect:/users/new";
        }
        try {
            userService.save(user);
        } catch (PhoneNotUniqueException e) {
            attr.addFlashAttribute("exPhone", e.getMessage());
            attr.addFlashAttribute("org.springframework.validation.BindingResult.user", binding);
            attr.addFlashAttribute("user", user);
            return "redirect:/register";
        } catch (EmailNotUniqueException e) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.user", binding);
            attr.addFlashAttribute("user", user);
            attr.addFlashAttribute("exEmail", e.getMessage());
            return "redirect:/register";
        } catch (UsernameNotUniqueException e) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.user", binding);
            attr.addFlashAttribute("user", user);
            attr.addFlashAttribute("exUsername", e.getMessage());
            if(user.getId() != null)
                return "redirect:/users/update/" + user.getId().toString();
            return "redirect:/users/new";
        }

        if(user.getAuthorities().contains(authorityService.getByRole("ROLE_CUSTOMER")))
            return "redirect:/users/myProfile";
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

    @RequestMapping("/update/{id}")
    public String updateUser(@PathVariable String id, Model model) {
        User user = userService.findById(Long.valueOf(id));
        if(!model.containsAttribute("user"))
            model.addAttribute("user", user);
        return "userform";
    }
}
