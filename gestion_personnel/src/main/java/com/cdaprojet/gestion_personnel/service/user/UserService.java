package com.cdaprojet.gestion_personnel.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.cdaprojet.gestion_personnel.model.user.User;

public interface UserService {
    
    UserDetailsService userDetailsService();
    User findByEmail(String email);

}
