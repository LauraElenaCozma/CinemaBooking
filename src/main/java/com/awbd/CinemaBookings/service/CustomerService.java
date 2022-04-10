package com.awbd.CinemaBookings.service;


import com.awbd.CinemaBookings.domain.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(Long id);
    Customer save(Customer customer);
    void deleteById(Long id);
}
