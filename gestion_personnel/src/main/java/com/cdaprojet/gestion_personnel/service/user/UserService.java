package com.cdaprojet.gestion_personnel.service.user;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.cdaprojet.gestion_personnel.model.user.User;
import com.cdaprojet.gestion_personnel.model.user.UserDto;

public interface UserService {
    
    UserDetailsService userDetailsService();
    User findByEmail(String email);
    UserDto getUser(String jwt);
    List<UserDto> getAllUser();
}
