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
                .antMatchers("/movies/{^[\\\\d]$}").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers("/movies/**").hasRole("ADMIN")
                .antMatchers("/showings/{^[\\\\d]$}").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers("/showings/**").hasAnyRole("ADMIN")
                .antMatchers("/actors/{^[\\\\d]$}").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers("/actors/**").hasAnyRole("ADMIN")
                .antMatchers("/venues/{^[\\\\d]$}").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers("/venues/**").hasAnyRole("ADMIN")
                .and()
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/authUser")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");
    }
}