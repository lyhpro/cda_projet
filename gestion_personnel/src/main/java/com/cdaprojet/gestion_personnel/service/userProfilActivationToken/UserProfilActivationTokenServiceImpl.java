package com.cdaprojet.gestion_personnel.service.userProfilActivationToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdaprojet.gestion_personnel.model.user.User;
import com.cdaprojet.gestion_personnel.model.userProfilActivationToken.UserProfilActivationToken;
import com.cdaprojet.gestion_personnel.repository.UserProfilActivationTokenRepository;
import com.cdaprojet.gestion_personnel.service.jwt.JwtService;

@Service
public class UserProfilActivationTokenServiceImpl implements UserProfilActivationTokenService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserProfilActivationTokenRepository userProfilActivationTokenRepository;

    @Override
    public UserProfilActivationToken create(User user) {
        String token = jwtService.generateActivatedUserToken(user);
        UserProfilActivationToken userProfilActivationToken = new UserProfilActivationToken(0, token, user);
        return userProfilActivationTokenRepository.save(userProfilActivationToken);
    }
    
}
