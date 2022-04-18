package com.awbd.CinemaBookings.domain.security;

import com.awbd.CinemaBookings.domain.Booking;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

import static com.awbd.CinemaBookings.dto.PatternPhoneNumber.PHONE_REGEX;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "The first name is required!")
    @Size(max = 50, message = "First name must not be longer than 50 characters")
    private String firstName;

    @NotEmpty(message = "The last name is required!")
    @Size(max = 50, message = "Last name must not be longer than 50 characters")
    private String lastName;

    @Email
    @NotEmpty(message = "The email is required!")
    @Size(max = 50, message = "The email must not be longer than 50 characters")
    private String email;

    @NotEmpty(message = "The phone number is required!")
    @Size(max = 50, message = "Phone number must not be longer than 50 characters")
    @Pattern(regexp = PHONE_REGEX, message = "Invalid phone number")
    private String phoneNumber;

    @NotEmpty(message = "The username is required!")
    @Size(max = 20, message = "Username must not be longer than 20 characters")
    private String username;

    @NotEmpty(message = "The password is required!")
    @Size(min = 3, message = "The password must have minimum 3 characters")
    private String password;

    @Singular
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name =
            "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id",
                    referencedColumnName = "id"))
    private Set<Authority> authorities;

    @Builder.Default
    private Boolean enabled = true;

    @Builder.Default
    private Boolean accountNotExpired = true;

    @Builder.Default
    private Boolean accountNotLocked = true;

    @Builder.Default
    private Boolean credentialsNotExpired = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> bookings;
}