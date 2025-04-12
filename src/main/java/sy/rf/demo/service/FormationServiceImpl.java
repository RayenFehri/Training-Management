package sy.rf.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sy.rf.demo.entity.Formation;
import sy.rf.demo.repository.FormationRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FormationServiceImpl implements FormationService {

    @Autowired
    private FormationRepository formationRepository;

    @Override
    public Formation createFormation(Formation formation) {
        formationRepository.save(formation);
        return formationRepository.findById(formation.getId())
                .orElseThrow(() -> new RuntimeException("Formation non trouvée après création"));
    }

    @Override
    public Formation getFormationById(UUID id) {
        return formationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formation non trouvée"));
    }

    @Override
    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }

    @Override
    public Formation updateFormation(UUID id, Formation updatedFormation) {
        Formation formation = getFormationById(id);
        formation.setTitre(updatedFormation.getTitre());
        formation.setAnnee(updatedFormation.getAnnee());
        formation.setDuree(updatedFormation.getDuree());
        formation.setBudget(updatedFormation.getBudget());
        formation.setDomaine(updatedFormation.getDomaine());
        formation.setFormateur(updatedFormation.getFormateur());
        return formationRepository.save(formation);
    }

    @Override
    public void deleteFormation(UUID id) {
        formationRepository.deleteById(id);
    }
}
