package com.awbd.CinemaBookings.repository;

import com.awbd.CinemaBookings.domain.Booking;
import com.awbd.CinemaBookings.domain.security.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {
    public List<Booking> findAllByUser(User user);
}
