package com.cdaprojet.gestion_personnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdaprojet.gestion_personnel.model.userProfilActivationToken.UserProfilActivationToken;

@Repository
public interface UserProfilActivationTokenRepository extends JpaRepository<UserProfilActivationToken,Long> {
    
}
