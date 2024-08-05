package com.cdaprojet.gestion_personnel.service.userPwdActivationToken;

import com.cdaprojet.gestion_personnel.model.user.User;
import com.cdaprojet.gestion_personnel.model.userPwdActivationToken.UserPwdActivationToken;

public interface UserPwdActivationTokenService {
    UserPwdActivationToken create(User user);
}
