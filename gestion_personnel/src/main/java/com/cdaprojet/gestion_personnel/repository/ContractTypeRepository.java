package com.cdaprojet.gestion_personnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdaprojet.gestion_personnel.model.contractType.ContractType;

@Repository
public interface ContractTypeRepository extends JpaRepository<ContractType,Long> {
    
}
