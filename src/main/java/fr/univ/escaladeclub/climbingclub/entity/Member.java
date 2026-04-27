package fr.univ.escaladeclub.climbingclub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a member of the climbing club.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Member's last name.
     */
    private String lastName;

    /**
     * Member's first name.
     */
    private String firstName;

    /**
     * Member's email address.
     */
    private String email;

    /**
     * Member's password (hashed in production).
     */
    private String password;
    /**
     * List of outings created by the member.
     */
    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Outing> outings;

    public void addOuting(Outing outing) {
        if (outings == null) {
            outings = new ArrayList<>();
        }
        outings.add(outing);
        outing.setCreator(this);
    }
}