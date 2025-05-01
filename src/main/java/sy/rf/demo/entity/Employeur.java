package sy.rf.demo.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "employeur")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nomemployeur;
}
