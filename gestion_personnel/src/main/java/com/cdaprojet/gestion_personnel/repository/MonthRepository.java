package com.cdaprojet.gestion_personnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdaprojet.gestion_personnel.model.time.month.Month;

@Repository
public interface MonthRepository extends JpaRepository<Month,Long> {
    
}
