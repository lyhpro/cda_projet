package com.cdaprojet.gestion_personnel.service.signuprequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.SignupRequest;
import com.cdaprojet.gestion_personnel.model.role.Role;
import com.cdaprojet.gestion_personnel.model.user.User;
import com.cdaprojet.gestion_personnel.model.user.UserDto;
import com.cdaprojet.gestion_personnel.model.userProfilActivationToken.UserProfilActivationToken;
import com.cdaprojet.gestion_personnel.repository.RoleRepository;
import com.cdaprojet.gestion_personnel.repository.UserRepository;
import com.cdaprojet.gestion_personnel.service.email.EmailService;
import com.cdaprojet.gestion_personnel.service.user.UserServiceImpl;
import com.cdaprojet.gestion_personnel.service.userProfilActivationToken.UserProfilActivationTokenService;

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
    private UserProfilActivationTokenService userProfilActivationTokenService;

    @Autowired
    private EmailService emailService;

    @Override
    public UserDto signup(SignupRequest signupRequest) {
        User newUser = new User();
        if(userServiceImpl.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        } else {
            newUser = this.create(signupRequest);
            UserProfilActivationToken userProfilActivationToken = this.userProfilActivationTokenService.create(newUser);
    
            String newuserFullname = newUser.getFirstname() + " " + newUser.getSecondname();
            String newuserEmail = newUser.getEmail();
    
            emailService.sendActivatedUserEmail(newuserFullname, newuserEmail, userProfilActivationToken.getId());
        }

        return new UserDto(newUser);
    }
    
    public User create(SignupRequest signupRequest) {
        Role role = roleRepository.findByName(signupRequest.getRoleName());
        String password = signupRequest.getSecondname().toLowerCase() + signupRequest.getFirstname().toLowerCase();
        String encryptedPassword = passwordEncoder.encode(password);
        User user = new User(signupRequest.getSecondname(), signupRequest.getFirstname(), signupRequest.getEmail(), encryptedPassword, role, false, false);
        return userRepository.save(user);
    }
}
