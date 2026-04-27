package fr.univ.escaladeclub.climbingclub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
//comment
/**
 * Represents an outing organized by a club member.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Outing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the outing.
     */
    private String name;

    /**
     * Description of the outing.
     */
    private String description;

    /**
     * Web link with more information (optional).
     */
    private String website;

    /**
     * Date of the outing.
     */
    private LocalDate date;

    /**
     * Category to which this outing belongs.
     */
    @ManyToOne
    private Category category;

    /**
     * The member who created the outing.
     */
    @ManyToOne
    private Member creator;
}