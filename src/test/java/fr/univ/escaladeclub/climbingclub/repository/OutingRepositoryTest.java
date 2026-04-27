package fr.univ.escaladeclub.climbingclub.repository;

import fr.univ.escaladeclub.climbingclub.entity.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for the OutingRepository.
 */
@DataJpaTest
public class OutingRepositoryTest {

    @Autowired
    private OutingRepository outingRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Test finding outings by their associated category ID.
     */
    @Test
    @DisplayName("Should find outings by category ID")
    void testFindByCategoryId() {
        Member member = memberRepository.save(new Member(null, "Smith", "Alice", "alice@club.com", "pass", null));
        Category category = categoryRepository.save(new Category(null, "Alpine", null));

        Outing outing = new Outing(null, "Alpine Ascent", "A tough climb", "http://example.com",
                LocalDate.of(2025, 5, 1), category, member);
        outingRepository.save(outing);

        List<Outing> results = outingRepository.findByCategoryId(category.getId());

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getName()).isEqualTo("Alpine Ascent");
    }

    /**
     * Test finding outings by the ID of the member who created them.
     */
    @Test
    @DisplayName("Should find outings by creator ID")
    void testFindByCreatorId() {
        Member member = memberRepository.save(new Member(null, "Smith", "Alice", "alice@club.com", "pass", null));
        Category category = categoryRepository.save(new Category(null, "Alpine", null));

        Outing outing = new Outing(null, "Alpine Challenge", "Steep path", "http://example.com",
                LocalDate.of(2025, 5, 1), category, member);
        outingRepository.save(outing);

        List<Outing> results = outingRepository.findByCreatorId(member.getId());

        assertThat(results).hasSize(1);
        assertThat(results.get(0).getDescription()).isEqualTo("Steep path");
    }

    /**
     * Test searching outings by keywords in name or description.
     */
    @Test
    @DisplayName("Should find outings by keyword in name or description")
    void testSearchByNameOrDescription() {
        Member member = memberRepository.save(new Member(null, "Lee", "Bruce", "bruce@club.com", "dragon", null));
        Category category = categoryRepository.save(new Category(null, "Rock", null));

        Outing outing1 = new Outing(null, "Rock Climb", "Challenging vertical route", null, LocalDate.now(), category, member);
        Outing outing2 = new Outing(null, "Valley Walk", "Peaceful and scenic", null, LocalDate.now(), category, member);
        outingRepository.saveAll(List.of(outing1, outing2));

        List<Outing> results = outingRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase("rock", "scenic");

        assertThat(results).hasSize(2);
    }
}