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

    @GetMapping("/sendEmail")
    public ResponseEntity<String> sendEmail() {
        emailService.sendSimpleMailMessage("test name", "lyheunpha-ly@hotmail.fr", "token");
        return ResponseEntity.ok("Email envoyé");
    }
}
