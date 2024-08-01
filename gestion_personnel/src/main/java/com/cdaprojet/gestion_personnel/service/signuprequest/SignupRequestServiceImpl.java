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
import com.cdaprojet.gestion_personnel.service.email.EmailService;
import com.cdaprojet.gestion_personnel.service.jwt.JwtService;
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

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EmailService emailService;

    @Override
    public UserDto signup(SignupRequest signupRequest) {
        if(userServiceImpl.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Role role = roleRepository.findByName(signupRequest.getRoleName());
        String password = signupRequest.getSecondname().toLowerCase() + signupRequest.getFirstname().toLowerCase();
        String encryptedPassword = passwordEncoder.encode(password);
        User user = new User(signupRequest.getSecondname(), signupRequest.getFirstname(), signupRequest.getEmail(), encryptedPassword, role, false, false);
        User newUser = userRepository.save(user);
        String newuserFullname = newUser.getFirstname() + " " + newUser.getSecondname();
        String token = jwtService.generateActivatedUserToken(newUser);
        emailService.sendActivatedUserEmail(newuserFullname, newUser.getEmail(), token);

        return new UserDto(newUser);
    }
    
}
