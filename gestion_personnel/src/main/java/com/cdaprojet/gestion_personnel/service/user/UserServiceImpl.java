package com.cdaprojet.gestion_personnel.service.user;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.user.User;
import com.cdaprojet.gestion_personnel.model.user.UserDto;
import com.cdaprojet.gestion_personnel.repository.UserRepository;
import com.cdaprojet.gestion_personnel.service.jwt.JwtServiceImpl;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtServiceImpl jwtServiceImpl;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public UserDto getUser(String jwt) {
        String newToken = jwt.replace("Bearer", "");
        long idUser = Long.valueOf(jwtServiceImpl.extractUserId(newToken));
        User user = userRepository.findById(idUser).orElse(null);
        if(Objects.nonNull(user)) {
            return new UserDto(user);
        }
        return null;
    }

    @Override
    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream().map(UserDto::new).toList();
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}