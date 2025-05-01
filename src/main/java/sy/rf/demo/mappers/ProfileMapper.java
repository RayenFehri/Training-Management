package sy.rf.demo.mappers;

import sy.rf.demo.dto.ProfileDto;
import sy.rf.demo.entity.Profil;

public class ProfileMapper {

    public static ProfileDto toDto(Profil profil) {
        if (profil == null) return null;

        return ProfileDto.builder()
                .id(profil.getId())
                .libelle(profil.getLibelle())
                .build();
    }

    public static Profil toEntity(ProfileDto profileDto) {
        if (profileDto == null) return null;

        return Profil.builder()
                .id(profileDto.getId())
                .libelle(profileDto.getLibelle())
                .build();
    }
}
