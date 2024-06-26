package com.cdaprojet.gestion_personnel.service.month;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.month.Month;
import com.cdaprojet.gestion_personnel.repository.MonthRepository;

@Service
public class MonthServiceImpl implements MonthService {
    
    @Autowired
    private MonthRepository monthRepository;

    @Override
    public List<Month> getAllMonth() {
        return monthRepository.findAll();
    }
}