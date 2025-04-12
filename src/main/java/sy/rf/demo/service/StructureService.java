package sy.rf.demo.service;

import sy.rf.demo.entity.Structure;

import java.util.List;
import java.util.UUID;

public interface StructureService {
    Structure createStructure(Structure structure);
    Structure getStructureById(UUID id);
    List<Structure> getAllStructures();
    Structure updateStructure(UUID id, Structure structure);
    void deleteStructure(UUID id);
}
