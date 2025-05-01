package sy.rf.demo.dto;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormationDto {
    private UUID id;

    private String titre;
    private Integer annee;
    private Integer duree;
    private Double budget;
    private DomaineDto domaine;
    private FormateurDto formateur;

}
