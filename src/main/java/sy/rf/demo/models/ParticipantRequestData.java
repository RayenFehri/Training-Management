package sy.rf.demo.models;

import lombok.Getter;
import sy.rf.demo.entity.Formation;

import java.util.Set;

@Getter
public class ParticipantRequestData {
    private String nom;
    private String prenom;
    private String email;
    private Long tel;
    private Long structureId;
    private Long profilId;
    private Set<Formation> formations ;
}
