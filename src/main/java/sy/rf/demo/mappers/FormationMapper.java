package sy.rf.demo.mappers;

import sy.rf.demo.dto.FormationDto;
import sy.rf.demo.entity.Formation;

public class FormationMapper {

    public static FormationDto toDto(Formation formation) {
        if (formation == null) return null;

        return FormationDto.builder()
                .id(formation.getId())
                .titre(formation.getTitre())
                .annee(formation.getAnnee())
                .duree(formation.getDuree())
                .budget(formation.getBudget())
                .domaine(DomaineMapper.toDto(formation.getDomaine()))
                .formateur(FormateurMapper.toDto(formation.getFormateur()))
                .build();
    }

    public static Formation toEntity(FormationDto dto) {
        if (dto == null) return null;

        return Formation.builder()
                .id(dto.getId())
                .titre(dto.getTitre())
                .annee(dto.getAnnee())
                .duree(dto.getDuree())
                .budget(dto.getBudget())
                .domaine(DomaineMapper.toEntity(dto.getDomaine()))
                .formateur(FormateurMapper.toEntity(dto.getFormateur()))
                .build();
    }
}
