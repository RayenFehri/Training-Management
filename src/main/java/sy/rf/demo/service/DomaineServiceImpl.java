package sy.rf.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sy.rf.demo.entity.Domaine;
import sy.rf.demo.repository.DomaineRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DomaineServiceImpl implements DomaineService {

    private final DomaineRepository domaineRepository;

    @Override
    public Domaine createDomaine(Domaine domaine) {
        return domaineRepository.save(domaine);
    }

    @Override
    public Domaine getDomaineById(UUID id) {
        return domaineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Domaine non trouvé"));
    }

    @Override
    public List<Domaine> getAllDomaines() {
        return domaineRepository.findAll();
    }

    @Override
    public Domaine updateDomaine(UUID id, Domaine updatedDomaine) {
        Domaine domaine = getDomaineById(id);
        domaine.setLibelle(updatedDomaine.getLibelle());
        return domaineRepository.save(domaine);
    }

    @Override
    public void deleteDomaine(UUID id) {
        domaineRepository.deleteById(id);
    }
}
