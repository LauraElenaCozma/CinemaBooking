package com.awbd.CinemaBookings.service.security;

import com.awbd.CinemaBookings.domain.security.User;
import com.awbd.CinemaBookings.exception.EmailNotUniqueException;
import com.awbd.CinemaBookings.exception.PhoneNotUniqueException;
import com.awbd.CinemaBookings.exception.UserNotFoundException;
import com.awbd.CinemaBookings.exception.UsernameNotUniqueException;
import com.awbd.CinemaBookings.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        Optional<User> userEmail = userRepository.findByEmail(user.getEmail());
        if(userEmail.isPresent() && !Objects.equals(user.getId(), userEmail.get().getId())) {
            // an user with the same email already exists;
            // 1. the saved user did not changed the email => userEmail.id = user.id
            // 2. the saved user changed the email => another user was found with this email
            throw new EmailNotUniqueException(user.getEmail());
        }
        Optional<User> userPhoneNumber = userRepository.findByPhoneNumber(user.getPhoneNumber());
        if(userPhoneNumber.isPresent() && !Objects.equals(user.getId(), userPhoneNumber.get().getId()))
            throw new PhoneNotUniqueException(user.getPhoneNumber());

        Optional<User> userUsername = userRepository.findByUsername(user.getUsername());
        if(userUsername.isPresent() && !Objects.equals(user.getId(), userUsername.get().getId()))
            throw new UsernameNotUniqueException(user.getUsername());

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

    private void checkUniqueEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent())
            throw new EmailNotUniqueException(email);
    }

    private void checkUniquePhoneNumber(String phone) {
        Optional<User> user = userRepository.findByPhoneNumber(phone);
        if(user.isPresent())
            throw new PhoneNotUniqueException(phone);
    }
}
