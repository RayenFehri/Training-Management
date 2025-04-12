package sy.rf.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sy.rf.demo.entity.Structure;
import sy.rf.demo.repository.StructureRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StructureServiceImpl implements StructureService {

    @Autowired
    private StructureRepository structureRepository;

    @Override
    public Structure createStructure(Structure structure) {
        structureRepository.save(structure);
        return structureRepository.findById(structure.getId())
                .orElseThrow(() -> new RuntimeException("Structure non trouvée après création"));
    }

    @Override
    public Structure getStructureById(UUID id) {
        return structureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Structure non trouvée"));
    }

    @Override
    public List<Structure> getAllStructures() {
        return structureRepository.findAll();
    }

    @Override
    public Structure updateStructure(UUID id, Structure updatedStructure) {
        Structure structure = getStructureById(id);
        structure.setLibelle(updatedStructure.getLibelle());
        return structureRepository.save(structure);
    }

    @Override
    public void deleteStructure(UUID id) {
        structureRepository.deleteById(id);
    }
}
