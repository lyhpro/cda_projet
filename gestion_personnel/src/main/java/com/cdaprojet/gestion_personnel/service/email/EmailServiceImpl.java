package com.cdaprojet.gestion_personnel.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendSimpleMailMessage(String name, String to, String token) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("Activation de votre compte");
            message.setFrom("gestionnairepersonnel.hly@gmail.com");
            message.setTo(to);
            String fakeUrl = "http://localhost:4200/auth";
            String text = 
                "<p>Bonjour " +name+".</p>" +
                "<p>Bienvenue sur l'application de gesion de personnel. Vous troouverez ci-dessous un lien pour activer votre compte.</p>"+
                "<a href=\""+fakeUrl+"\">Activer votre compte</a>" + 
                "<p>Merci.</p>" + 
                "<p>L'equipe support.</p>";
            message.setText(text);
            javaMailSender.send(message);
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
        
    }
}
