package com.awbd.CinemaBookings.repository.security;

import com.awbd.CinemaBookings.domain.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Optional<Authority> getByRole(String role);
}
