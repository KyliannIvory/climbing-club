package fr.univ.escaladeclub.climbingclub.web.controller;

import fr.univ.escaladeclub.climbingclub.service.MailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {

    private final MailService mailService;

    public ContactController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/contact")
    public String handleContactForm(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String message,
            RedirectAttributes redirectAttributes) {

        String subject = "Nouveau message de contact - Climbing Club";
        String content = "Nom : " + name + "\nEmail : " + email + "\n\nMessage :\n" + message;

        mailService.sendEmail("climbingclub@club.fr", subject, content);

        redirectAttributes.addFlashAttribute("success", "Votre message a bien été envoyé !");
        return "redirect:/home";
    }
}