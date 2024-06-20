package com.cdaprojet.gestion_personnel.service.dayType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.dayType.DayType;
import com.cdaprojet.gestion_personnel.repository.DayTypeRepository;

@Service
public class DayTypeServiceImple implements DayTypeService {
    
    @Autowired
    private DayTypeRepository dayTypeRepository;

    @Override
    public List<DayType> getAllDayType() {
        return dayTypeRepository.findAll().stream().filter(dayType -> dayType.isEnable()).toList();
    }
    
}
