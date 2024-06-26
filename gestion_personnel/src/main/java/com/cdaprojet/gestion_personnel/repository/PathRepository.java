package com.cdaprojet.gestion_personnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdaprojet.gestion_personnel.model.pathModel.path.Path;

@Repository
public interface PathRepository extends JpaRepository<Path,Long>{
    
    Path findByAlias(String aliasRoute);
    
}
