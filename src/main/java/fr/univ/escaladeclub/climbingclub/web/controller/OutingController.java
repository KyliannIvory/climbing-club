package fr.univ.escaladeclub.climbingclub.web.controller;

import fr.univ.escaladeclub.climbingclub.entity.Member;
import fr.univ.escaladeclub.climbingclub.entity.Outing;
import fr.univ.escaladeclub.climbingclub.service.CategoryService;
import fr.univ.escaladeclub.climbingclub.service.MemberService;
import fr.univ.escaladeclub.climbingclub.service.OutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OutingController {

    @Autowired
    private OutingService outingService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/outings/{id}")
    public String showOutingDetails(@PathVariable Long id, Model model) {
        model.addAttribute("outing", outingService.getOuting(id));
        return "outing-details";
    }

    @GetMapping("/search")
    public String searchOutings(@RequestParam("q") String keyword, Model model) {
        List<Outing> results = outingService.searchOutings(keyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("results", results);
        return "search-results";
    }

    //Formulaire d'ajout
    @GetMapping("/outings/new")
    public String showCreateForm(Model model) {
        model.addAttribute("outing", new Outing());
        model.addAttribute("categories", categoryService.findAll());
        return "outing-form";
    }

    //Traitement des formulaires
    @PostMapping("/outings")
    public String createOrUpdateOuting(@ModelAttribute("outing") Outing outing) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) auth.getPrincipal()).getUsername();
        Member member = memberService.findByEmail(email);

        // Si c'est une nouvelle sortie
        if (outing.getId() == null) {
            member.addOuting(outing);
            outingService.createOuting(member.getId(), outing);
        } else {
            outing.setCreator(member); // au cas où
            outingService.updateOuting(outing.getId(), outing);
        }

        return "redirect:/dashboard";
    }

    @GetMapping("/outings/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Outing outing = outingService.getOuting(id); // ou outingService.getOutingForUser(id, currentUser)
        model.addAttribute("outing", outing);
        model.addAttribute("categories", categoryService.findAll());
        return "outing-form";
    }

    @GetMapping("/outings/delete/{id}")
    public String deleteOuting(@PathVariable Long id) {
        outingService.deleteOuting(id);
        return "redirect:/dashboard";
    }
}
