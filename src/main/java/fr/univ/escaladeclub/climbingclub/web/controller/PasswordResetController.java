package fr.univ.escaladeclub.climbingclub.web.controller;

import fr.univ.escaladeclub.climbingclub.service.MailService;
import fr.univ.escaladeclub.climbingclub.service.MemberService;
import fr.univ.escaladeclub.climbingclub.service.ResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class PasswordResetController {

    @Autowired
    private MailService mailService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ResetTokenService resetTokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Formulaire GET
    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password";
    }

    // Traitement POST
    @PostMapping("/forgot-password")
    public String handleForgotPassword(@RequestParam String email, Model model) {
        try {
            var member = memberService.findByEmail(email);
            String token = UUID.randomUUID().toString();

            // Store token en mémoire
            resetTokenService.storeToken(token, member);

            // Envoi d’un email contenant un lien de réinitialisation (version simple pour l’instant)
            String resetLink = "http://localhost:8080/reset-password?token=" + token;

            mailService.sendPasswordResetEmail(email, resetLink);

            model.addAttribute("message", "Un lien de réinitialisation a été envoyé à votre adresse.");
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Aucun compte trouvé avec cet email.");
        }

        return "forgot-password";
    }

    @GetMapping("/reset-password")
    public String showResetForm(@RequestParam String token, Model model) {
        var member = resetTokenService.getMemberByToken(token);
        if (member == null) {
            model.addAttribute("error", "Lien invalide ou expiré.");
            return "reset-password";
        }

        model.addAttribute("token", token); // on garde le token pour le POST
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String handleReset(@RequestParam String token,
                              @RequestParam String newPassword,
                              Model model) {

        var member = resetTokenService.getMemberByToken(token);
        if (member == null) {
            model.addAttribute("error", "Lien invalide ou expiré.");
            return "reset-password";
        }

        member.setPassword(passwordEncoder.encode(newPassword));
        memberService.createMember(member); // save/override

        resetTokenService.removeToken(token);
        model.addAttribute("message", "Mot de passe réinitialisé avec succès !");
        return "redirect:/login?resetSuccess"; // ou redirect:/login
    }
}