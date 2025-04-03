package sy.rf.demo.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "structure")
@Data
public class Structure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String libelle;
}
