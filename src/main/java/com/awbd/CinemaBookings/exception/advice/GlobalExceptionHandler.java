package com.awbd.CinemaBookings.exception.advice;

import com.awbd.CinemaBookings.domain.Movie;
import com.awbd.CinemaBookings.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public ModelAndView handleNotFound(NotFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("notfound");
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }
}
