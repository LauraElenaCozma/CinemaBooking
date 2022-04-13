package com.awbd.CinemaBookings.service;

import com.awbd.CinemaBookings.domain.Booking;
import com.awbd.CinemaBookings.exception.BookingNotFoundException;
import com.awbd.CinemaBookings.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> findAll() {
        List<Booking> bookings = new LinkedList<>();
        bookingRepository.findAll().iterator().forEachRemaining(bookings::add);
        return bookings;
    }

    @Override
    public Booking findById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));
    }

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Booking update(Long id, Booking updatedBooking) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if(booking.isEmpty())
            throw new BookingNotFoundException(id);
        Booking newBooking = booking.get();
        newBooking.setNumReservedSeats(updatedBooking.getNumReservedSeats());
        return bookingRepository.save(newBooking);
    }

    @Override
    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }
}
