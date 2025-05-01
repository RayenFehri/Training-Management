package sy.rf.demo.service;

import sy.rf.demo.dto.ProfileDto;
import java.util.List;

public interface ProfilService {
    ProfileDto createProfil(ProfileDto profil);
    ProfileDto getProfilById(Long id);
    List<ProfileDto> getAllProfils();
    ProfileDto updateProfil(ProfileDto profil);
    void deleteProfil(Long id);
}
