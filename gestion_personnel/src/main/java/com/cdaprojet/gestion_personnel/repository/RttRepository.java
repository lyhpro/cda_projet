package com.cdaprojet.gestion_personnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdaprojet.gestion_personnel.model.specialDay.rtt.Rtt;

@Repository
public interface RttRepository extends JpaRepository<Rtt,Long> {
    
}
