package com.cdaprojet.gestion_personnel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdaprojet.gestion_personnel.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    
    Optional<User> findByEmail(String email);
    User findByRoleId(long id);
    List<User> findAllByRoleId(long id);
    boolean existsByEmail(String email);
    
}
