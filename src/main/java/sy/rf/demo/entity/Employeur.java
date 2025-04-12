package sy.rf.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "employeur")
@Data

public class Employeur {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String nomemployeur;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomemployeur() {
        return nomemployeur;
    }

    public void setNomemployeur(String nomemployeur) {
        this.nomemployeur = nomemployeur;
    }
}
