package com.awbd.CinemaBookings.service;

import com.awbd.CinemaBookings.domain.Info;
import com.awbd.CinemaBookings.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class InfoServiceImpl implements InfoService{

    InfoRepository infoRepository;

    @Autowired
    public InfoServiceImpl(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    @Override
    public List<Info> findAll() {
        List<Info> moviesInfo = new LinkedList<>();
        infoRepository.findAll().iterator().forEachRemaining(moviesInfo::add);
        return moviesInfo;
    }

    @Override
    public Info findById(Long id) {
        return infoRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Info save(Info movieInfo) {
        return infoRepository.save(movieInfo);
    }

    @Override
    public void deleteById(Long id) {
        infoRepository.deleteById(id);
    }
}
