package sy.rf.demo.dto;

import lombok.*;
import sy.rf.demo.entity.Formation;

import java.util.Set;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private Long tel;  // ou String selon votre besoin
    private StructureDto structure;
    private ProfileDto profil;
    private Set<Formation> formations ;

}
