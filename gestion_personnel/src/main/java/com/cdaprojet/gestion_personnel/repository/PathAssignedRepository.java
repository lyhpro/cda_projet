package com.cdaprojet.gestion_personnel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdaprojet.gestion_personnel.model.pathModel.pathAssigned.PathAssigned;

@Repository
public interface PathAssignedRepository extends JpaRepository<PathAssigned,Long>{
    
    List<PathAssigned> findAllByRoleIdAndEnable(long roleId, boolean enable);
    
}
