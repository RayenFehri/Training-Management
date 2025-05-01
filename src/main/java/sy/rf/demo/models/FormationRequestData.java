package sy.rf.demo.models;

import lombok.Getter;
import sy.rf.demo.dto.DomaineDto;
import sy.rf.demo.dto.FormateurDto;

import java.util.UUID;
@Getter
public class FormationRequestData {
    private UUID id;

    private String titre;
    private Integer annee;
    private Integer duree;
    private Double budget;
    private Long domaineId;
    private Long formateurId;
}
