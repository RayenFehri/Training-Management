package sy.rf.demo.service;

import sy.rf.demo.entity.Profil;

import java.util.List;
import java.util.UUID;

public interface ProfilService {
    Profil createProfil(Profil profil);
    Profil getProfilById(UUID id);
    List<Profil> getAllProfils();
    Profil updateProfil(UUID id, Profil profil);
    void deleteProfil(UUID id);
}
