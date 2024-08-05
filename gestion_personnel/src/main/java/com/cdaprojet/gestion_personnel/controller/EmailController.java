package com.cdaprojet.gestion_personnel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdaprojet.gestion_personnel.service.email.EmailService;

@RestController
@RequestMapping("/gestionnaire-personnel/email")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class EmailController {
    
    @Autowired
    private EmailService emailService;

    @GetMapping("/sendActivatedUserEmail")
    public ResponseEntity<String> sendActivatedUserEmail(String userFullname, String userEmail, long tokenId) {
        emailService.sendActivatedUserEmail(userFullname, userEmail, tokenId);
        return ResponseEntity.ok("Email d'activation du profil utilisateur envoyé");
    }

    @GetMapping("/sendUpdatePasswordUserEmail")
    public ResponseEntity<String> sendCreatedPwdUserEmail(String userFullname, String userEmail, long tokenId) {
        emailService.sendCreatedPwdUserEmail(userFullname, userEmail, tokenId);
        return ResponseEntity.ok("Email de création de mot de passe utilisateur envoyé");
    }
}
