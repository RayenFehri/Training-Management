package sy.rf.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Table(name = "formateur")
@Data
public class Formateur {

    @Id
    private UUID id; // L'ID du Formateur est le même que celui de User

    @OneToOne
    @MapsId // Utiliser le même ID que User
    @JoinColumn(name = "id", referencedColumnName = "id")
    private User user;

    private String nom;
    private String prenom;
    private Long tel;
    private String type; // "interne" ou "externe"

    @ManyToOne
    @JoinColumn(name = "id_employeur")
    private Employeur employeur;

    public Formateur() {
    }

    public Formateur(User user, String nom, String prenom, Long tel, String type, Employeur employeur) {
        this.user = user;
        this.id = user.getId(); // Lier l'ID du Formateur à celui de User
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.type = type;
        this.employeur = employeur;
    }
}
