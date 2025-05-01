package sy.rf.demo.service.impl;


import org.springframework.stereotype.Service;
import sy.rf.demo.dto.ProfileDto;
import sy.rf.demo.mappers.ProfileMapper;
import sy.rf.demo.repository.ProfilRepository;
import sy.rf.demo.service.ProfilService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfilServiceImpl implements ProfilService {


    private  final ProfilRepository profilRepository;
 public ProfilServiceImpl(ProfilRepository profilRepository) {
    this.profilRepository = profilRepository;
}
    @Override
    public ProfileDto createProfil(ProfileDto profil) {
        return ProfileMapper.toDto(profilRepository.save(ProfileMapper.toEntity(profil)));
    }

    @Override
    public ProfileDto getProfilById(Long id) {
        return ProfileMapper.toDto(profilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profil non trouvé")));
    }

    @Override
    public List<ProfileDto> getAllProfils() {
        return profilRepository.findAll().stream().map(ProfileMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProfileDto updateProfil(ProfileDto updatedProfil) {
        return ProfileMapper.toDto(profilRepository.save(ProfileMapper.toEntity(updatedProfil)));
    }

    @Override
    public void deleteProfil(Long id) {
        profilRepository.deleteById(id);
    }
}
