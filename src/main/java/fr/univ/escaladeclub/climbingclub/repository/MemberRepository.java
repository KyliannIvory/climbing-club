package fr.univ.escaladeclub.climbingclub.repository;


import fr.univ.escaladeclub.climbingclub.entity.Member;
import fr.univ.escaladeclub.climbingclub.entity.Outing;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Member entity.
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * Find a member by their email address.
     */
    Optional<Member> findByEmail(String email);

    /**
     * Find a member by ID and load their outings eagerly.
     */
    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.outings WHERE m.id = :id")
    Optional<Member> findByIdWithOutings(Long id);
}
