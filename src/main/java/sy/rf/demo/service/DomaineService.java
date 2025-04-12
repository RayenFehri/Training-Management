package sy.rf.demo.service;

import sy.rf.demo.entity.Domaine;

import java.util.List;
import java.util.UUID;

public interface DomaineService {
    Domaine createDomaine(Domaine domaine);
    Domaine getDomaineById(UUID id);
    List<Domaine> getAllDomaines();
    Domaine updateDomaine(UUID id, Domaine domaine);
    void deleteDomaine(UUID id);
}
