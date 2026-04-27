package fr.univ.escaladeclub.climbingclub.service;


import fr.univ.escaladeclub.climbingclub.entity.Member;
import fr.univ.escaladeclub.climbingclub.entity.Outing;
import fr.univ.escaladeclub.climbingclub.repository.MemberRepository;
import fr.univ.escaladeclub.climbingclub.repository.OutingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class OutingService {

    @Autowired
    private OutingRepository outingRepository;

    @Autowired
    private MemberRepository memberRepository;

    public List<Outing> getAllOutings() {
        return outingRepository.findAll();
    }

    public Outing getOuting(Long outingId) {
        return outingRepository.findById(outingId)
                .orElseThrow(()-> new IllegalArgumentException("Outing not found"));
    }

    public Outing createOuting(Long memberId, Outing outing) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalArgumentException("Member not found"));
        member.addOuting(outing);
        return outingRepository.save(outing);
    }

    public Outing updateOuting(Long outingId, Outing outing) {
        Outing oldOuting = getOuting(outingId);
        oldOuting.setName(outing.getName());
        oldOuting.setDescription(outing.getDescription());
        oldOuting.setWebsite(outing.getWebsite());
        oldOuting.setDate(outing.getDate());
        oldOuting.setCategory(outing.getCategory());
        oldOuting.setCreator(outing.getCreator());
        return outingRepository.save(oldOuting);
    }

    public void deleteOuting(Long outingId) {
        Outing oldOuting = getOuting(outingId);
        outingRepository.delete(oldOuting);
    }
    public List<Outing> searchOutings(String keyword) {
        return outingRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }

    public List<Outing> getUpcomingOutings() {
        return outingRepository.findAll().stream()
                .filter(o -> o.getDate().isAfter(LocalDate.now()))
                .sorted(Comparator.comparing(Outing::getDate))
                .limit(12)
                .toList();
    }

    public List<Outing> findByCreator(Member member) {
        return outingRepository.findByCreatorId(member.getId());
    }
}
