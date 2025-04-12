package sy.rf.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sy.rf.demo.entity.Profil;
import sy.rf.demo.repository.ProfilRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfilServiceImpl implements ProfilService {

    @Autowired
    private ProfilRepository profilRepository;

    @Override
    public Profil createProfil(Profil profil) {
        return profilRepository.save(profil);
    }

    @Override
    public Profil getProfilById(UUID id) {
        return profilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profil non trouvé"));
    }

    @Override
    public List<Profil> getAllProfils() {
        return profilRepository.findAll();
    }

    @Override
    public Profil updateProfil(UUID id, Profil updatedProfil) {
        Profil profil = getProfilById(id);
        profil.setLibelle(updatedProfil.getLibelle());
        return profilRepository.save(profil);
    }

    @Override
    public void deleteProfil(UUID id) {
        profilRepository.deleteById(id);
    }
}
