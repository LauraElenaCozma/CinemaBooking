package com.awbd.CinemaBookings.service.security;

import com.awbd.CinemaBookings.domain.security.Authority;
import com.awbd.CinemaBookings.repository.security.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;

    public Authority getByRole(String role) {
        Optional<Authority> authorityOptional = authorityRepository.getByRole(role);
        if(authorityOptional.isEmpty())
            throw new RuntimeException("Role does not exist");
        return authorityOptional.get();
    }
}
