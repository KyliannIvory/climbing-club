package fr.univ.escaladeclub.climbingclub.repository;


import fr.univ.escaladeclub.climbingclub.entity.Outing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for Outing entity.
 */
public interface OutingRepository extends JpaRepository<Outing, Long> {


    /**
     * Find all outings that belong to a given category ID.
     */
    List<Outing> findByCategoryId(Long categoryId);

    /**
     * Find all outings created by a specific member.
     */
    List<Outing> findByCreatorId(Long creatorId);

    /**
     * Search outings by name containing a specific keyword (case insensitive).
     */
    List<Outing> findByNameContainingIgnoreCase(String keyword);

    List<Outing> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String nameKeyword, String descKeyword);
}
