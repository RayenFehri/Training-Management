package sy.rf.demo.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "formation")
@Data
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String titre;
    private Integer annee;
    private Integer duree; // nombre de jours
    private Double budget;

    @ManyToOne
    @JoinColumn(name = "id_domaine")
    private Domaine domaine;





    //  Si une formation doit avoir un formateur
    @ManyToOne
    @JoinColumn(name = "id_formateur")
    private Formateur formateur;

    // Getters & Setters
}

