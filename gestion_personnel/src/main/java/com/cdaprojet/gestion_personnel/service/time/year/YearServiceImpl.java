package com.cdaprojet.gestion_personnel.service.time.year;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.time.year.Year;
import com.cdaprojet.gestion_personnel.repository.YearRepository;

@Service
public class YearServiceImpl implements YearService {
    
    @Autowired
    private YearRepository yearRepository;

    @Override
    public List<Year> getAllYear() {
        return yearRepository.findAll();
    }
}
