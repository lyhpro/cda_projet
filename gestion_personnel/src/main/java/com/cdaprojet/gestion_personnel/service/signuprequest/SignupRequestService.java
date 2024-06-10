package com.cdaprojet.gestion_personnel.service.signuprequest;

import com.cdaprojet.gestion_personnel.model.SignupRequest;
import com.cdaprojet.gestion_personnel.model.user.UserDto;

public interface SignupRequestService {
    
    UserDto signup(SignupRequest signupRequest);
}
