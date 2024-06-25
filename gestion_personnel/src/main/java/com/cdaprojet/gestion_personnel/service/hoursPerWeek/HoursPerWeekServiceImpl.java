package com.cdaprojet.gestion_personnel.service.hoursPerWeek;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.hoursPerWeek.HoursPerWeek;
import com.cdaprojet.gestion_personnel.repository.HoursPerWeekRepository;

@Service
public class HoursPerWeekServiceImpl implements HoursPerWeekService {
    
    @Autowired
    private HoursPerWeekRepository hoursPerWeekRepository;

    @Override
    public List<HoursPerWeek> getAllHoursPerWeek() {
        return hoursPerWeekRepository.findAll()
        .stream()
        .filter(hoursPerWeek -> hoursPerWeek.isEnable())
        .toList();
    }
}
