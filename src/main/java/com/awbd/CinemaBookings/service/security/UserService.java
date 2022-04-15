package com.awbd.CinemaBookings.service.security;

import com.awbd.CinemaBookings.domain.security.User;
import com.awbd.CinemaBookings.exception.UserNotFoundException;
import com.awbd.CinemaBookings.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {

        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            Optional<User> user = userRepository.findByUsername(username);
            if(user.isEmpty())
                throw new UserNotFoundException();
            return user.get();
        } else throw new UserNotFoundException();
    }
}
