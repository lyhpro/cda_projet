package com.cdaprojet.gestion_personnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdaprojet.gestion_personnel.model.employeeModel.professionalDetail.ProfessionalDetail;

@Repository
public interface ProfessionalDetailRepository extends JpaRepository<ProfessionalDetail,Long> {
    
}
