package com.cdaprojet.gestion_personnel.service.userProfilActivationToken;

import com.cdaprojet.gestion_personnel.model.user.User;
import com.cdaprojet.gestion_personnel.model.userProfilActivationToken.UserProfilActivationToken;

public interface UserProfilActivationTokenService {
    UserProfilActivationToken create(User user);
}
