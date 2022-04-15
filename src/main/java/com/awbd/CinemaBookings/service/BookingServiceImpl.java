package com.awbd.CinemaBookings.service;

import com.awbd.CinemaBookings.domain.Booking;
import com.awbd.CinemaBookings.domain.MovieShowing;
import com.awbd.CinemaBookings.domain.security.User;
import com.awbd.CinemaBookings.exception.BookingNotFoundException;
import com.awbd.CinemaBookings.exception.NotAvailableSeatsException;
import com.awbd.CinemaBookings.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    BookingRepository bookingRepository;

    MovieShowingService movieShowingService;

    public BookingServiceImpl(BookingRepository bookingRepository, MovieShowingService movieShowingService) {
        this.bookingRepository = bookingRepository;
        this.movieShowingService = movieShowingService;
    }

    @Override
    public List<Booking> findAll() {
        List<Booking> bookings = new LinkedList<>();
        bookingRepository.findAll().iterator().forEachRemaining(bookings::add);
        return bookings;
    }

    @Override
    public List<Booking> findAllByUser(User user) {
        List<Booking> bookings = new LinkedList<>();
        bookingRepository.findAllByUser(user).iterator().forEachRemaining(bookings::add);
        return bookings;
    }

    @Override
    public Booking findById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));
    }

    @Override
    public Booking save(Booking booking) {
        MovieShowing movieShowing = booking.getMovieShowing();
        Integer numAvailable = movieShowingService.getNumberOfAvailableSeats(movieShowing.getId());
        if (numAvailable - booking.getNumReservedSeats() >= 0)
            return bookingRepository.save(booking);
        else throw new NotAvailableSeatsException();
    }

    @Override
    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }
}
