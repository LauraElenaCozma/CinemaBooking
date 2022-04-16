package com.awbd.CinemaBookings.controller;

import com.awbd.CinemaBookings.domain.*;
import com.awbd.CinemaBookings.domain.security.Authority;
import com.awbd.CinemaBookings.domain.security.User;
import com.awbd.CinemaBookings.dto.ActorFound;
import com.awbd.CinemaBookings.exception.NotAvailableSeatsException;
import com.awbd.CinemaBookings.service.BookingService;
import com.awbd.CinemaBookings.service.MovieShowingService;
import com.awbd.CinemaBookings.service.security.AuthorityService;
import com.awbd.CinemaBookings.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private MovieShowingService movieShowingService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;


    @RequestMapping("/bookings/new")
    public String createBooking(@RequestParam(required = false) Long showingId, Model model) {
        if(!model.containsAttribute("booking"))
            model.addAttribute("booking", new Booking());
        model.addAttribute("showingsAll", movieShowingService.findAll());
        model.addAttribute("showingId", showingId);

        return "bookingform";
    }

    @PostMapping("/bookings")
    public String saveOrUpdateBooking(@Valid @ModelAttribute("booking") Booking booking, BindingResult bindingBooking,
                                    RedirectAttributes attr) {
        if(bindingBooking.hasErrors()) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.booking", bindingBooking);
            attr.addFlashAttribute("booking", booking);
            if(booking.getId() != null)
                return "redirect:/bookings/update/" + booking.getId().toString();
            return "redirect:/bookings/new";
        }
        User currentUser = userService.getCurrentUser();
        booking.setUser(currentUser);
        booking.setBookingDate(new Date());
        Integer numSeatsAvailable = movieShowingService.getNumberOfAvailableSeats(booking.getMovieShowing().getId());
        try {
            bookingService.save(booking);
        } catch (NotAvailableSeatsException e) {
            attr.addFlashAttribute("org.springframework.validation.BindingResult.booking", bindingBooking);
            attr.addFlashAttribute("booking", booking);
            attr.addFlashAttribute("seatsException", "There are not enough seats available in the venue.\n");
            attr.addFlashAttribute("seatsAvailable", numSeatsAvailable);
            if(booking.getId() != null)
                return "redirect:/bookings/update/" + booking.getId().toString();
            return "redirect:/bookings/new";
        }
        if(currentUser.getAuthorities().contains(authorityService.getByRole("ROLE_CUSTOMER")))
            return "redirect:/bookings/myBookings";
        return "redirect:/bookings";
    }

    @RequestMapping("/bookings")
    public ModelAndView getAllBookings() {
        ModelAndView modelAndView = new ModelAndView("bookings");
        List<Booking> bookings = bookingService.findAll();
        modelAndView.addObject("bookings", bookings);
        return modelAndView;
    }

    @RequestMapping("/bookings/myBookings")
    public ModelAndView getMyBookings() {
        ModelAndView modelAndView = new ModelAndView("bookings");
        User currentUser = userService.getCurrentUser();
        List<Booking> bookings = bookingService.findAllByUser(currentUser);
        modelAndView.addObject("bookings", bookings);
        return modelAndView;
    }

    @GetMapping("/bookings/{id}")
    public String getBooking(@PathVariable String id, Model model) {
        Booking booking = bookingService.findById(Long.valueOf(id));
        model.addAttribute("booking", booking);
        return "bookinginfo";
    }

    @RequestMapping("/bookings/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteById(id);
        User currentUser = userService.getCurrentUser();
        if(currentUser.getAuthorities().contains(authorityService.getByRole("ROLE_CUSTOMER")))
            return "redirect:/bookings/myBookings";
        return "redirect:/bookings";
    }

    @RequestMapping("/bookings/update/{id}")
    public String updateBooking(@PathVariable String id, Model model) {
        Booking booking = bookingService.findById(Long.valueOf(id));
        if(!model.containsAttribute("booking"))
            model.addAttribute("booking", booking);
        model.addAttribute("showingsAll", movieShowingService.findAll());
        return "bookingform";
    }
}
