package com.awbd.CinemaBookings.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "index").hasAnyRole("CUSTOMER", "ADMIN")

                .antMatchers("/movies/new").hasRole("ADMIN")
                .antMatchers("/movies").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers("/movies/{\\\\d+}").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers( "/movies/**").hasRole("ADMIN")
                .antMatchers("/showings/new").hasRole("ADMIN")
                .antMatchers("/showings").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers("/showings/{\\\\d+}").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers( "/showings/**").hasRole("ADMIN")
                .antMatchers("/actors/new").hasRole("ADMIN")
                .antMatchers("/actors").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers("/actors/{\\\\d+}").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers( "/actors/**").hasRole("ADMIN")
                .antMatchers("/venues/new").hasRole("ADMIN")
                .antMatchers("/venues").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers("/venues/{\\\\d+}").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers( "/venues/**").hasRole("ADMIN")
                .antMatchers("/bookings/{^[0-9]+}").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers("/bookings/new", "/bookings/myBookings").hasRole("CUSTOMER")
                .antMatchers("/bookings/update/**", "/bookings/delete/**").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/bookings").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/bookings").hasRole("CUSTOMER")
                .antMatchers("/users/new").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/users").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers("/users").hasRole("ADMIN")
                .antMatchers("/users/{\\\\d+}").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers( "/users/delete/{\\\\d+}").hasAnyRole("ADMIN", "CUSTOMER")
                .antMatchers( "/users/update/{\\\\d+}").hasRole("CUSTOMER")
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().disable()
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/authUser")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");
    }
}