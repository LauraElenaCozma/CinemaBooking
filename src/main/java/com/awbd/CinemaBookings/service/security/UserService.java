package com.awbd.CinemaBookings.service.security;

import com.awbd.CinemaBookings.domain.Customer;
import com.awbd.CinemaBookings.domain.security.User;
import com.awbd.CinemaBookings.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }
}
