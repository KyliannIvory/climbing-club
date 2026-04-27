package fr.univ.escaladeclub.climbingclub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendTestEmail(String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Test - Climbing Club");
        message.setText("Ce message confirme que l'envoi d'email fonctionne avec Spring Boot + MailHog ✅");

        mailSender.send(message);
    }
    public void sendPasswordResetEmail(String to, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Réinitialisation de votre mot de passe - Climbing Club");
        message.setText("Bonjour,\n\n" +
                "Vous avez demandé à réinitialiser votre mot de passe. Cliquez sur le lien ci-dessous :\n" +
                resetLink + "\n\n" +
                "Si vous n'avez pas fait cette demande, ignorez simplement cet email.\n\n" +
                "— L'équipe Climbing Club");

        mailSender.send(message);
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}