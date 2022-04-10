package com.awbd.CinemaBookings.service;

import com.awbd.CinemaBookings.domain.Booking;
import java.util.List;

public interface BookingService {
    List<Booking> findAll();
    Booking findById(Long id);
    Booking save(Booking booking);
    Booking update(Long id, Booking updatedBooking);
    void deleteById(Long id);
}
