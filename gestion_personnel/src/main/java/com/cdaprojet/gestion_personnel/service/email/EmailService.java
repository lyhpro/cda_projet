package com.cdaprojet.gestion_personnel.service.email;

public interface EmailService {
    
    void sendSimpleMailMessage(String name, String to, String token);

}
