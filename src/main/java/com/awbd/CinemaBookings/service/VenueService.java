package com.awbd.CinemaBookings.service;

import com.awbd.CinemaBookings.domain.Venue;

import java.util.List;

public interface VenueService {
    List<Venue> findAll();
    Venue findById(Long id);
    Venue save(Venue venue);
    void deleteById(Long id);
}
