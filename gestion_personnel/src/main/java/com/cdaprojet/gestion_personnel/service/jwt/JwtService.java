package com.cdaprojet.gestion_personnel.service.jwt;

import org.springframework.security.core.userdetails.UserDetails;

import com.cdaprojet.gestion_personnel.model.user.User;

public interface JwtService {
    
    String generateToken(User userDetails);
    String generateActivatedUserToken(User userDetails);
    String extractUserName(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
    boolean isTokenExpired(String token);

}
