package fr.univ.escaladeclub.climbingclub.web.controller;


import fr.univ.escaladeclub.climbingclub.entity.Member;
import fr.univ.escaladeclub.climbingclub.service.MemberService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("member", new Member());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute("member") Member member, Model model) {
        Member existingMember = null;
        try{
            existingMember = memberService.findByEmail(member.getEmail());
        }
        catch (IllegalArgumentException ignored){}
        if (existingMember != null) {
            model.addAttribute("error", "Email already in use");
            return "register";
        }
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberService.createMember(member);
        return "redirect:/login";
    }
}
