package com.cdaprojet.gestion_personnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdaprojet.gestion_personnel.model.role.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    
    Role findByName(String name);
    
}
