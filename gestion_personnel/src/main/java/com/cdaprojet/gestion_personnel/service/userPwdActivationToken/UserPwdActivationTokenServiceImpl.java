package com.cdaprojet.gestion_personnel.service.userPwdActivationToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.user.User;
import com.cdaprojet.gestion_personnel.model.userPwdActivationToken.UserPwdActivationToken;
import com.cdaprojet.gestion_personnel.repository.UserPwdActivationTokenRepository;
import com.cdaprojet.gestion_personnel.service.jwt.JwtService;

@Service
public class UserPwdActivationTokenServiceImpl implements UserPwdActivationTokenService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserPwdActivationTokenRepository userPwdActivationTokenRepository;

    @Override
    public UserPwdActivationToken create(User user) {
        String token = jwtService.generateActivatedUserToken(user);
        UserPwdActivationToken userPwdActivationToken = new UserPwdActivationToken(0, token, user);
        return userPwdActivationTokenRepository.save(userPwdActivationToken);

    }
    
}
