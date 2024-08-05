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
    public void sendActivatedUserEmail(String userFullname, String userEmail, long tokenId) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("Activation du profil utilisateur");
            message.setFrom("gestionnairepersonnel.hly@gmail.com");
            message.setTo(userEmail);
            String activateduserUrl = "http://localhost:4200/activated-user/"+tokenId;
            String text = 
                "<p>Bonjour " +userFullname+".</p>" +
                "<p>Bienvenue sur l'application de gesion de personnel. Vous trouverez ci-dessous un lien pour activer votre profil utilisateur.</p>"+
                "<a href=\""+activateduserUrl+"\">Activer votre profil utilisateur</a>" + 
                "<p>Merci.</p>" + 
                "<p>L'equipe support.</p>";
            message.setText(text);
            javaMailSender.send(message);
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
            throw new RuntimeException(exception.getMessage());
        }
        
    }

    @Override
    public void sendCreatedPwdUserEmail(String userFullname, String userEmail,long tokenId) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("Création d'un nouveau mot de passe");
            message.setFrom("gestionnairepersonnel.hly@gmail.com");
            message.setTo(userEmail);
            String activateduserUrl = "http://localhost:4200/create-password-user/"+tokenId;
            String text = 
                "<p>Bonjour " +userFullname+".</p>" +
                "<p>Vote profil utilisateur est désormais activé. Vous trouverez ci-dessous un lien pour créer un nouveau mot de passe.</p>"+
                "<a href=\""+activateduserUrl+"\">Créer votre nouveau mot de passe</a>" + 
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
