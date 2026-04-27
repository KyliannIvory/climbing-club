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
 * Tests for the CategoryRepository.
 */
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OutingRepository outingRepository;

    @Test
    @DisplayName("Should save and find all categories")
    void testSaveAndFindAll() {
        categoryRepository.save(new Category(null, "Rock Climbing", null));
        categoryRepository.save(new Category(null, "Mixed Climbing", null));

        List<Category> categories = categoryRepository.findAll();

        assertThat(categories).hasSize(2);
    }

    /**
     * Test creating, reading, updating, and deleting a category.
     */
    @Test
    @DisplayName("Should perform CRUD operations on Category")
    void testCRUDCategory() {
        Category category = new Category(null, "Ice Climbing", null);
        Category saved = categoryRepository.save(category);

        List<Category> all = categoryRepository.findAll();
        assertThat(all).contains(saved);

        saved.setName("Updated Ice Climbing");
        categoryRepository.save(saved);

        Category updated = categoryRepository.findById(saved.getId()).orElseThrow();
        assertThat(updated.getName()).isEqualTo("Updated Ice Climbing");

        categoryRepository.deleteById(saved.getId());
        assertThat(categoryRepository.findById(saved.getId())).isEmpty();
    }

    /**
     * Test saving multiple categories and retrieving them all.
     */
    @Test
    @DisplayName("Should find all saved categories")
    void testFindMultipleCategories() {
        Category cat1 = new Category(null, "Alpinism", null);
        Category cat2 = new Category(null, "Cave Exploration", null);
        categoryRepository.saveAll(List.of(cat1, cat2));

        List<Category> categories = categoryRepository.findAll();
        assertThat(categories).hasSizeGreaterThanOrEqualTo(2);
    }
    /**
     * Test finding a category along with its associated outings.
     */
    /*@Test
    @DisplayName("Should find category with its outings")
    void testFindByIdWithOutings() {
        Category category = categoryRepository.save(new Category(null, "Mountain Climbing", null));
        Member creator = memberRepository.save(new Member(null, "Adams", "Amy", "amy@club.com", "secure", null));

        Outing outing1 = new Outing(null, "Summit Push", "Final stage to the summit", null, LocalDate.now(), category, creator);
        Outing outing2 = new Outing(null, "Base Camp Trek", "Trek to base camp", null, LocalDate.now(), category, creator);

        outingRepository.saveAll(List.of(outing1, outing2));

        Category result = categoryRepository.findByIdWithOutings(category.getId()).orElseThrow();
        assertThat(result.getOutings()).hasSize(2);
    }

     */

}