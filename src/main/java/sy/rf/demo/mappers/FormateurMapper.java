package sy.rf.demo.mappers;

import sy.rf.demo.dto.FormateurDto;
import sy.rf.demo.entity.Formateur;

public class FormateurMapper {

    public static FormateurDto toDto(Formateur formateur) {
        if (formateur == null) return null;

        return FormateurDto.builder()
                .id(formateur.getId())
                .nom(formateur.getNom())
                .prenom(formateur.getPrenom())
                .email(formateur.getEmail())
                .tel(formateur.getTel())
                .type(formateur.getType())
                .employeur(EmployeurMapper.toDto(formateur.getEmployeur()))
                .user(UserMapper.toDto(formateur.getUser()))
                .build();
    }

    public static Formateur toEntity(FormateurDto dto) {
        if (dto == null) return null;

        return Formateur.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .email(dto.getEmail())
                .tel(dto.getTel())
                .type(dto.getType())
                .employeur(EmployeurMapper.toEntity(dto.getEmployeur()))
                .user(UserMapper.toEntity(dto.getUser()))
                .build();
    }
}
