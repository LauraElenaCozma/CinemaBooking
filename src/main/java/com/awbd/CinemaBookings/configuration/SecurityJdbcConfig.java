package com.awbd.CinemaBookings.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Profile("mysql")
public class SecurityJdbcConfig extends WebSecurityConfigurerAdapter {

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
                .antMatchers("/movies").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers("/movies/{^[\\\\d]$}").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers("/movies/**").hasRole("ADMIN")
                .antMatchers("/").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers("/index").hasAnyRole("CUSTOMER", "ADMIN")
                .antMatchers("/showings").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers("/showings/{^[\\\\d]$}").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers("/showings/**").hasAnyRole("ADMIN")
                .antMatchers("/actors").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers("/actors/{^[\\\\d]$}").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers("/actors/**").hasRole("ADMIN")
                .antMatchers("/venues").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers("/venues/{^[\\\\d]$}").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers("/venues/**").hasRole("ADMIN")
                .antMatchers("/bookings").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers("/bookings/{^[\\\\d]$}").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers("/bookings/(delete|update)/**").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers("/bookings/new").hasRole("CUSTOMER")
                .antMatchers("/bookings/myBookings").hasRole("CUSTOMER")
                .antMatchers("/users/{^[\\\\d]$}").hasRole("CUSTOMER")
                .antMatchers("/users/myProfile").hasRole("CUSTOMER")
                .antMatchers("/users/(delete|update)").hasRole("CUSTOMER")
                .antMatchers("/users/**").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/authUser")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");
    }
}