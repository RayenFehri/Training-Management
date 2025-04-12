package sy.rf.demo.service;

import sy.rf.demo.entity.Formation;

import java.util.List;
import java.util.UUID;

public interface FormationService {
    Formation createFormation(Formation formation);
    Formation getFormationById(UUID id);
    List<Formation> getAllFormations();
    Formation updateFormation(UUID id, Formation formation);
    void deleteFormation(UUID id);
}
