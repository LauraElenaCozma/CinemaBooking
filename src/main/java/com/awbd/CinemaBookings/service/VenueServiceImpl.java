package com.awbd.CinemaBookings.service;

import com.awbd.CinemaBookings.domain.Venue;
import com.awbd.CinemaBookings.exception.VenueNotFoundException;
import com.awbd.CinemaBookings.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {

    VenueRepository venueRepository;

    @Autowired
    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }


    @Override
    public List<Venue> findAll() {
        List<Venue> venues = new LinkedList<>();
        venueRepository.findAll().iterator().forEachRemaining(venues::add);
        return venues;
    }

    @Override
    public Venue findById(Long id) {
        return venueRepository.findById(id).orElseThrow(() -> new VenueNotFoundException(id));
    }

    @Override
    public Venue save(Venue venue) {
        return venueRepository.save(venue);
    }

    @Override
    public void deleteById(Long id) {
        venueRepository.deleteById(id);
    }
}
