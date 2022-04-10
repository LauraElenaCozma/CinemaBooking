package com.awbd.CinemaBookings.repository;

import com.awbd.CinemaBookings.domain.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {
}
