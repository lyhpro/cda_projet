package com.cdaprojet.gestion_personnel.service.signuprequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.SignupRequest;
import com.cdaprojet.gestion_personnel.model.role.Role;
import com.cdaprojet.gestion_personnel.model.user.User;
import com.cdaprojet.gestion_personnel.model.user.UserDto;
import com.cdaprojet.gestion_personnel.repository.RoleRepository;
import com.cdaprojet.gestion_personnel.repository.UserRepository;
import com.cdaprojet.gestion_personnel.service.user.UserServiceImpl;

@Service
public class SignupRequestServiceImpl implements SignupRequestService {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto signup(SignupRequest signupRequest) {
        if(userServiceImpl.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Role role = roleRepository.findByName(signupRequest.getRoleName());
        String password = signupRequest.getSecondname().toLowerCase() + signupRequest.getFirstname().toLowerCase();
        String encryptedPassword = passwordEncoder.encode(password);
        User user = new User(signupRequest.getSecondname(), signupRequest.getFirstname(), signupRequest.getEmail(), encryptedPassword, role, true);
        User newUser = userRepository.save(user);

        return new UserDto(newUser);
    }
    
}
