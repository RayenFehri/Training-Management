package sy.rf.demo.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "participant")
@Data
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nom;
    private String prenom;
    private String email;
    private Long tel;  // ou String selon votre besoin

    @ManyToOne
    @JoinColumn(name = "id_structure")
    private Structure structure;

    @ManyToOne
    @JoinColumn(name = "id_profil")
    private Profil profil;

    @ManyToMany
    @JoinTable(
            name = "formation_participant",
            joinColumns = @JoinColumn(name = "participant_id"),
            inverseJoinColumns = @JoinColumn(name = "formation_id")
    )
    private Set<Formation> formations = new HashSet<>();




}

