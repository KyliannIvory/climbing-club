package fr.univ.escaladeclub.climbingclub.repository;

import fr.univ.escaladeclub.climbingclub.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repository interface for Category entity.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.outings WHERE c.id = :id")
    Optional<Category> findByIdWithOutings(@Param("id")Long id);
}