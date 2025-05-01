package sy.rf.demo.service.impl;


import org.springframework.stereotype.Service;
import sy.rf.demo.dto.StructureDto;
import sy.rf.demo.mappers.StructureMapper;
import sy.rf.demo.repository.StructureRepository;
import sy.rf.demo.service.StructureService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StructureServiceImpl implements StructureService {

    private final StructureRepository structureRepository;
    public StructureServiceImpl(StructureRepository structureRepository) {
        this.structureRepository = structureRepository;
    }
    @Override
    public StructureDto createStructure(StructureDto structure) {
      return   StructureMapper.toDto(structureRepository.save(StructureMapper.toEntity(structure)));

    }

    @Override
    public StructureDto getStructureById(Long id) {
        return StructureMapper.toDto(structureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Structure non trouvée")));
    }

    @Override
    public List<StructureDto> getAllStructures() {
        return structureRepository.findAll().stream().map(StructureMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public StructureDto updateStructure( StructureDto updatedStructure) {
        if(!structureRepository.existsById(updatedStructure.getId())) {
            throw new RuntimeException("Structure does not exist");
        }
        return StructureMapper.toDto(structureRepository.save(StructureMapper.toEntity(updatedStructure)));
    }

    @Override
    public void deleteStructure(Long id) {
        structureRepository.deleteById(id);
    }
}
