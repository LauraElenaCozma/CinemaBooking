package com.awbd.CinemaBookings.repository;

import com.awbd.CinemaBookings.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
