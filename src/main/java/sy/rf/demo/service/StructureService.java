package sy.rf.demo.service;

import sy.rf.demo.dto.StructureDto;
import sy.rf.demo.entity.Structure;

import java.util.List;

public interface StructureService {
    StructureDto createStructure(StructureDto structure);
    StructureDto getStructureById(Long id);
    List<StructureDto> getAllStructures();
    StructureDto updateStructure( StructureDto structure);
    void deleteStructure(Long id);
}
