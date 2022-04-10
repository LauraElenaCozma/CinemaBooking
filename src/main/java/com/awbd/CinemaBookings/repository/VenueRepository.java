package com.awbd.CinemaBookings.repository;

import com.awbd.CinemaBookings.domain.Venue;
import org.springframework.data.repository.CrudRepository;

public interface VenueRepository extends CrudRepository<Venue, Long> {
}
