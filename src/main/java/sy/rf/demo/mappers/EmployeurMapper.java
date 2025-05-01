package sy.rf.demo.mappers;

import sy.rf.demo.dto.EmployeurDto;
import sy.rf.demo.entity.Employeur;

public class EmployeurMapper {

    public static EmployeurDto toDto(Employeur employeur) {
        if (employeur == null) return null;

        return EmployeurDto.builder()
                .id(employeur.getId())
                .nomemployeur(employeur.getNomemployeur())
                .build();
    }

    public static Employeur toEntity(EmployeurDto dto) {
        if (dto == null) return null;

        return Employeur.builder()
                .id(dto.getId())
                .nomemployeur(dto.getNomemployeur())
                .build();
    }
}
