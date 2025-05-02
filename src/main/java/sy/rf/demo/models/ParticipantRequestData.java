package sy.rf.demo.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class ParticipantRequestData {
    private String nom;
    private String prenom;
    private String email;
    private Long tel;
    private Long structureId;
    private Long profilId;
    private Set<UUID> formationIds;     
}