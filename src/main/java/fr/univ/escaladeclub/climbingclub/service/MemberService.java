package fr.univ.escaladeclub.climbingclub.service;


import fr.univ.escaladeclub.climbingclub.entity.Member;
import fr.univ.escaladeclub.climbingclub.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    public Member updateMember(Member UpdatedMember) {
        Member member = findById(UpdatedMember.getId());
        member.setLastName(UpdatedMember.getLastName());
        member.setFirstName(UpdatedMember.getFirstName());
        member.setEmail(UpdatedMember.getEmail());
        return memberRepository.save(member);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member with id " + id + " not found"));
    }

    public void deleteById(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new IllegalArgumentException("Cannot delete. Member with id " + id + " does not exist.");
        }
        memberRepository.deleteById(id);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("No member with email " + email));
    }

    public Member findByIdWithOutings(Long id) {
        return memberRepository.findByIdWithOutings(id)
                .orElseThrow(() -> new IllegalArgumentException("Member with id " + id + " not found"));
    }
}
