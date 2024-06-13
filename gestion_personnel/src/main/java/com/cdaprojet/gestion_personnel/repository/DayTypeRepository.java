package com.cdaprojet.gestion_personnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdaprojet.gestion_personnel.model.dayType.DayType;

@Repository
public interface DayTypeRepository extends JpaRepository<DayType,Long> {
    
}
