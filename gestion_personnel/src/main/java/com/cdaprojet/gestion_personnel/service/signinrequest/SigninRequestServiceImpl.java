package com.cdaprojet.gestion_personnel.service.signinrequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.JwtResponse;
import com.cdaprojet.gestion_personnel.model.SigninRequest;
import com.cdaprojet.gestion_personnel.model.user.User;
import com.cdaprojet.gestion_personnel.repository.UserRepository;
import com.cdaprojet.gestion_personnel.service.jwt.JwtService;

@Service
public class SigninRequestServiceImpl implements SigninRequestService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired 
    private UserRepository userRepository;


    @Override
    public JwtResponse signin(SigninRequest signinRequest) {
        
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword()));
        User user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        String jwt = jwtService.generateToken(user);

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setJwt(jwt);

        return jwtResponse;

    }

    @Override
    public boolean activatedUser(String token) {
        String userEmail = jwtService.extractUserName(token);
        if(userEmail == null || jwtService.isTokenExpired(token)) {
            return false;
        }

        User user = userRepository.findByEmail(userEmail).orElse(null);
        user.setEnable(true);
        userRepository.save(user);
        return true;
    }
    
}