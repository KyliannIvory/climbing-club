package fr.univ.escaladeclub.climbingclub.repository;

import fr.univ.escaladeclub.climbingclub.entity.Member;
import fr.univ.escaladeclub.climbingclub.entity.Outing;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for the MemberRepository.
 */
@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OutingRepository outingRepository;

    @Test
    @DisplayName("Should save and find member by email")
    void testSaveAndFindByEmail() {
        Member member = new Member(null, "Doe", "John", "john@example.com", "pass123", null);
        memberRepository.save(member);

        Optional<Member> found = memberRepository.findByEmail("john@example.com");

        assertThat(found).isPresent();
        assertThat(found.get().getFirstName()).isEqualTo("John");
    }

    @Test
    @DisplayName("Should perform CRUD operations on Member")
    void testCRUD() {
        Member member = new Member(null, "Doe", "Jane", "jane@club.com", "testpass", null);
        Member saved = memberRepository.save(member);

        Optional<Member> found = memberRepository.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("jane@club.com");

        saved.setLastName("Updated");
        memberRepository.save(saved);

        Optional<Member> updated = memberRepository.findById(saved.getId());
        assertThat(updated).isPresent();
        assertThat(updated.get().getLastName()).isEqualTo("Updated");

        memberRepository.deleteById(saved.getId());
        assertThat(memberRepository.findById(saved.getId())).isEmpty();
    }

    @Test
    @DisplayName("Should find member by ID with outings")
    void testFindByIdWithOutings() {
        Member member = new Member(null, "Lee", "Bruce", "bruce@club.com", "dragon", null);
        Member savedMember = memberRepository.save(member);

        Outing outing1 = new Outing(null, "Dragon Peak", "Hard climb", null, LocalDate.now(), null, savedMember);
        Outing outing2 = new Outing(null, "Silent Valley", "Easy trek", null, LocalDate.now(), null, savedMember);

        savedMember.addOuting(outing1);
        savedMember.addOuting(outing2);

        outingRepository.saveAll(List.of(outing1, outing2));
        memberRepository.save(savedMember);

        Optional<Member> found = memberRepository.findByIdWithOutings(savedMember.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getOutings()).hasSize(2);
    }
}