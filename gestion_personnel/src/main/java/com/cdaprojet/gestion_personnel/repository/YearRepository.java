package com.cdaprojet.gestion_personnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdaprojet.gestion_personnel.model.time.year.Year;

@Repository
public interface YearRepository extends JpaRepository<Year,Long> {
    
}
