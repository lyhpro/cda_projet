package com.cdaprojet.gestion_personnel.service.email;

public interface EmailService {
    
    void sendActivatedUserEmail(String userFullname, String userEmail, String token);
    void sendUpdatedPwdUserEmail(String userFullname, String userEmail, String token);

}
