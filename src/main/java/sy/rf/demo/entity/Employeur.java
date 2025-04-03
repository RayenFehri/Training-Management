package sy.rf.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "employeur")
@Data

public class Employeur {
    @Id
    private UUID id; // L'ID du Formateur est le même que celui de User

    @OneToOne
    @MapsId // Utiliser le même ID que User
    @JoinColumn(name = "id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private String nomemployeur;
}
