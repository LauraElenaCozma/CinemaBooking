package com.awbd.CinemaBookings.bootstrap;

import com.awbd.CinemaBookings.domain.security.Authority;
import com.awbd.CinemaBookings.domain.security.User;
import com.awbd.CinemaBookings.repository.security.AuthorityRepository;
import com.awbd.CinemaBookings.repository.security.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }

    private void loadUserData() {
        if (userRepository.count() == 0) {
            Authority adminRole =
                    authorityRepository.save(Authority.builder().role("ROLE_ADMIN").build());
            Authority guestRole =
                    authorityRepository.save(Authority.builder().role("ROLE_CUSTOMER").build());
            User admin = User.builder()
                    .username("admin")
                    .firstName("Maia")
                    .lastName("Popescu")
                    .email("maiapop@gmail.com")
                    .phoneNumber("0723919229")
                    .password(passwordEncoder.encode("1234"))
                    .authority(adminRole)
                    .build();
            User guest = User.builder()
                    .username("customer")
                    .firstName("Erika")
                    .lastName("Sandy")
                    .email("erikasandy@gmail.com")
                    .phoneNumber("0722198832")
                    .password(passwordEncoder.encode("1234"))
                    .authority(guestRole)
                    .build();
            userRepository.save(admin);
            userRepository.save(guest);
        }
    }

}
