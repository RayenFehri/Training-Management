package sy.rf.demo.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "domaine")
@Data
public class Domaine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String libelle;
}
