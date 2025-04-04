package sy.rf.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;


@Entity
@Table(name = "domaine")
@Data
public class Domaine {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String libelle;
}
