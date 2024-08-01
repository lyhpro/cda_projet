package com.cdaprojet.gestion_personnel.service.signinrequest;

import com.cdaprojet.gestion_personnel.model.JwtResponse;
import com.cdaprojet.gestion_personnel.model.SigninRequest;

public interface SigninRequestService {
    
    JwtResponse signin(SigninRequest signinRequest);
    boolean activatedUser(String token);

}