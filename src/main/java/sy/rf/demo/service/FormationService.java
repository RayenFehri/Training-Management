package sy.rf.demo.service;

import sy.rf.demo.dto.FormationDto;
import sy.rf.demo.entity.Formation;

import java.util.List;
import java.util.UUID;

public interface FormationService {
    FormationDto createFormation(FormationDto formationDto);
    FormationDto getFormationById(UUID id);
    Formation getFormationEntityById(UUID id);
    List<FormationDto> getAllFormations();
    FormationDto updateFormation(FormationDto formationDto);
    void deleteFormation(UUID id);
}
