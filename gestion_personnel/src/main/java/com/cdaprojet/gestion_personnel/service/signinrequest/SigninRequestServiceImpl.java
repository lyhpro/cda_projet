package com.cdaprojet.gestion_personnel.service.signinrequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.JwtResponse;
import com.cdaprojet.gestion_personnel.model.SigninRequest;
import com.cdaprojet.gestion_personnel.model.user.User;
import com.cdaprojet.gestion_personnel.model.userProfilActivationToken.UserProfilActivationToken;
import com.cdaprojet.gestion_personnel.model.userPwdActivationToken.UserPwdActivationToken;
import com.cdaprojet.gestion_personnel.repository.UserProfilActivationTokenRepository;
import com.cdaprojet.gestion_personnel.repository.UserPwdActivationTokenRepository;
import com.cdaprojet.gestion_personnel.repository.UserRepository;
import com.cdaprojet.gestion_personnel.service.email.EmailService;
import com.cdaprojet.gestion_personnel.service.jwt.JwtService;
import com.cdaprojet.gestion_personnel.service.userPwdActivationToken.UserPwdActivationTokenService;

@Service
public class SigninRequestServiceImpl implements SigninRequestService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserProfilActivationTokenRepository userProfilActivationTokenRepository;

    @Autowired
    private UserPwdActivationTokenService userPwdActivationTokenService;
    
    @Autowired
    private UserPwdActivationTokenRepository userPwdActivationTokenRepository;

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
    public boolean activatedUser(long tokenId) {
        UserProfilActivationToken userProfilActivationToken = userProfilActivationTokenRepository.findById(tokenId).orElse(null);
        boolean isActivated = this.activatedProfilUser(userProfilActivationToken);
        if(isActivated) {
            String userFullname = userProfilActivationToken.getUser().getFirstname() + " " + userProfilActivationToken.getUser().getSecondname();
            String userEmail = userProfilActivationToken.getUser().getEmail();
            UserPwdActivationToken newUserPwdActivationToken = userPwdActivationTokenService.create(userProfilActivationToken.getUser());
            emailService.sendCreatedPwdUserEmail(userFullname, userEmail, newUserPwdActivationToken.getId());
        }
        return isActivated;
    }

    @Override   
    public boolean canUpdatePwdUser(long tokenId) {
        UserPwdActivationToken userPwdActivationToken = userPwdActivationTokenRepository.findById(tokenId).orElse(null);
        if(userPwdActivationToken == null || jwtService.isTokenExpired(userPwdActivationToken.getUserpwdActivationToken())) {
            return false;
        }
        return true;

    }

    public boolean activatedProfilUser(UserProfilActivationToken userProfilActivationToken) {
        if(userProfilActivationToken == null || jwtService.isTokenExpired(userProfilActivationToken.getUserprofilActivationToken())) {
            return false;
        }

        User user = userRepository.findById(userProfilActivationToken.getUser().getId()).orElse(null);
        user.setEnable(true);
        userRepository.save(user);
        return true;
    }

    
}