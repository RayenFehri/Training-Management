package sy.rf.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sy.rf.demo.entity.Formateur;
import sy.rf.demo.repository.FormateurRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FormateurServiceImpl implements FormateurService {

    @Autowired
    FormateurRepository formateurRepository;

    @Override
    public Formateur createFormateur(Formateur formateur) {
        return formateurRepository.save(formateur);
    }

    @Override
    public Formateur getFormateurById(UUID id) {
        return formateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formateur non trouvé"));
    }

    @Override
    public List<Formateur> getAllFormateurs() {
        return formateurRepository.findAll();
    }

    @Override
    public Formateur updateFormateur(UUID id, Formateur updatedFormateur) {
        Formateur formateur = getFormateurById(id);
        formateur.setNom(updatedFormateur.getNom());
        formateur.setPrenom(updatedFormateur.getPrenom());
        formateur.setEmail(updatedFormateur.getEmail());
        formateur.setTel(updatedFormateur.getTel());
        formateur.setType(updatedFormateur.getType());
        formateur.setEmployeur(updatedFormateur.getEmployeur());
        formateur.setUser(updatedFormateur.getUser());
        return formateurRepository.save(formateur);
    }

    @Override
    public void deleteFormateur(UUID id) {
        formateurRepository.deleteById(id);
    }
}
