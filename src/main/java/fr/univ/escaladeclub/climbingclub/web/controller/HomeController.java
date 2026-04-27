package fr.univ.escaladeclub.climbingclub.web.controller;

import fr.univ.escaladeclub.climbingclub.entity.Member;
import fr.univ.escaladeclub.climbingclub.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private OutingService outingService;

    @Autowired
    private CategoryService categoryService;

   /* @GetMapping("/home")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        String email = userDetails.getUsername();
        String firstname = memberService.findByEmail(email).getFirstName();

        model.addAttribute("message", "Bienvenue "+ firstname +" dans le Climbing Club");
        return "home";
    }

    */



   @GetMapping("/home")
   public String home(Model model, Authentication auth) {
       String email = null;

       if (auth != null && auth.getPrincipal() instanceof UserDetails userDetails) {
           email = userDetails.getUsername();
           Member member = memberService.findByEmail(email);
           model.addAttribute("message", "Bienvenue " + member.getFirstName() + " dans le Climbing Club");
       } else {
           model.addAttribute("message", "Bienvenue sur le Climbing Club !");
       }

       model.addAttribute("categories", categoryService.findAll());
       model.addAttribute("upcomingOutings", outingService.getUpcomingOutings());

       return "home";
   }

}
