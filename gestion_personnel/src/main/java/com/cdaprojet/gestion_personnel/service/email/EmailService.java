package com.cdaprojet.gestion_personnel.service.email;

public interface EmailService {
    
    void sendActivatedUserEmail(String userFullname, String userEmail, long tokenId);
    void sendCreatedPwdUserEmail(String userFullname, String userEmail, long tokenId);

}
