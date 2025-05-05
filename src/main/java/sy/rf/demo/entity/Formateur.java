package sy.rf.demo.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "formateur")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Formateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String tel;

    private String type;

    @ManyToOne
    @JoinColumn(name = "id_employeur")
    private Employeur employeur;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;


}


