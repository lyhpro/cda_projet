package com.cdaprojet.gestion_personnel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdaprojet.gestion_personnel.model.userPwdActivationToken.UserPwdActivationToken;

@Repository
public interface UserPwdActivationTokenRepository extends JpaRepository <UserPwdActivationToken, Long> {
    
}
