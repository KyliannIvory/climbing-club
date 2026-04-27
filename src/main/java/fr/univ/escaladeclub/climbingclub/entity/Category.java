package fr.univ.escaladeclub.climbingclub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Represents a category of climbing activity (e.g., rock climbing, alpine climbing).
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the category.
     */
    private String name;

    /**
     * List of outings belonging to this category.
     */
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Outing> outings;

    @Override
    public String toString() {
        return name;
    }
}