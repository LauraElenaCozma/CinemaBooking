package com.awbd.CinemaBookings.controller;

import com.awbd.CinemaBookings.domain.security.User;
import com.awbd.CinemaBookings.exception.EmailNotUniqueException;
import com.awbd.CinemaBookings.exception.PhoneNotUniqueException;
import com.awbd.CinemaBookings.exception.UsernameNotUniqueException;
import com.awbd.CinemaBookings.service.security.AuthorityService;
import com.awbd.CinemaBookings.service.security.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    UserService userService;

    @Autowired
    AuthorityService authorityService;

    @GetMapping("/login")
    public String showLogInForm() {
        log.info("Login");
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError() {
        log.info("Login error");
        return "loginerror";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        log.info("Access denied");
        return "accessdenied";
    }

    @GetMapping("/register")
    public String register(Model model){
        log.info("Register");
        if(!model.containsAttribute("user"))
            model.addAttribute("user", new User());
        return "registerform";
    }


    @PostMapping("/process_register")
    public String processRegister(@Valid @ModelAttribute("user")User user, BindingResult bindingResult,
                                  RedirectAttributes attr) {
        if (bindingResult.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            attr.addFlashAttribute("user", user);
            return "redirect:/register";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        User newUser = User.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(encodedPassword)
                .authority(authorityService.getByRole("ROLE_CUSTOMER"))
                .build();
        try {
            userService.save(newUser);
        } catch (PhoneNotUniqueException e) {
            attr.addFlashAttribute("exPhone", e.getMessage());
            attr.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            attr.addFlashAttribute("user", user);
            return "redirect:/register";
        } catch (EmailNotUniqueException e) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            attr.addFlashAttribute("user", user);
            attr.addFlashAttribute("exEmail", e.getMessage());
            return "redirect:/register";
        } catch (UsernameNotUniqueException e) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            attr.addFlashAttribute("user", user);
            attr.addFlashAttribute("exUsername", e.getMessage());
            if(user.getId() != null)
                return "redirect:/users/update/" + user.getId().toString();
            return "redirect:/register";
        }

        return "redirect:/login";
    }
}