package sy.rf.demo.mappers;

import sy.rf.demo.dto.DomaineDto;
import sy.rf.demo.entity.Domaine;

public class DomaineMapper {

    public static DomaineDto toDto(Domaine domaine) {
        if (domaine == null) return null;

        return DomaineDto.builder()
                .id(domaine.getId())
                .libelle(domaine.getLibelle())
                .build();
    }

    public static Domaine toEntity(DomaineDto dto) {
        if (dto == null) return null;

        return Domaine.builder()
                .id(dto.getId())
                .libelle(dto.getLibelle())
                .build();
    }
}
