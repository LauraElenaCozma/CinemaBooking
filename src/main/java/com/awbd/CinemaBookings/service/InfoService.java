package com.awbd.CinemaBookings.service;


import com.awbd.CinemaBookings.domain.Info;

import java.util.List;

public interface InfoService {
    List<Info> findAll();
    Info findById(Long id);
    Info save(Info movieInfo);
    void deleteById(Long id);
}
