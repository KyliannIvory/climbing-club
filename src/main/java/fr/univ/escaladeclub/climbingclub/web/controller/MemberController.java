package fr.univ.escaladeclub.climbingclub.web.controller;

import fr.univ.escaladeclub.climbingclub.entity.Member;
import fr.univ.escaladeclub.climbingclub.entity.Outing;
import fr.univ.escaladeclub.climbingclub.service.MemberService;
import fr.univ.escaladeclub.climbingclub.service.OutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private OutingService outingService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) auth.getPrincipal()).getUsername();

        Member member = memberService.findByEmail(email);
        model.addAttribute("member", member);
        model.addAttribute("outings", member.getOutings());
        return "dashboard";
    }

    @GetMapping("/members/{id}/outings")
    public String viewOutingsByMember(@PathVariable Long id, Model model) {
        Member member = memberService.findById(id);
        List<Outing> outings = outingService.findByCreator(member);
        model.addAttribute("member", member);
        model.addAttribute("outings", outings);
        return "member-outings";
    }
}
