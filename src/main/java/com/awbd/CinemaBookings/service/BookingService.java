package com.awbd.CinemaBookings.service;

import com.awbd.CinemaBookings.domain.Booking;
import com.awbd.CinemaBookings.domain.security.User;

import java.util.List;

public interface BookingService {
    List<Booking> findAll();
    List<Booking> findAllByUser(User user);
    Booking findById(Long id);
    Booking save(Booking booking);
    void deleteById(Long id);
}
