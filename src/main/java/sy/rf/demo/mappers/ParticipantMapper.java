package sy.rf.demo.mappers;


import sy.rf.demo.dto.ParticipantDto;
import sy.rf.demo.entity.Participant;
public class ParticipantMapper {

    public static ParticipantDto toDto(Participant participant) {
        if (participant == null) return null;

        return ParticipantDto.builder()
                .id(participant.getId())
                .nom(participant.getNom())
                .prenom(participant.getPrenom())
                .email(participant.getEmail())
                .tel(participant.getTel())
                .structure(StructureMapper.toDto(participant.getStructure()))
                .profil(ProfileMapper.toDto(participant.getProfil()))
                .formations(participant.getFormations())
                .build();
    }

    public static Participant toEntity(ParticipantDto dto) {
        if (dto == null) return null;

        return Participant.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .email(dto.getEmail())
                .tel(dto.getTel())
                .structure(StructureMapper.toEntity(dto.getStructure()))
                .profil(ProfileMapper.toEntity(dto.getProfil()))
                .formations(dto.getFormations())
                .build();
    }
}
