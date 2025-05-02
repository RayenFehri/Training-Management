package sy.rf.demo.service.impl;

import org.springframework.stereotype.Service;
import sy.rf.demo.dto.FormationDto;
import sy.rf.demo.entity.Formation;
import sy.rf.demo.mappers.FormationMapper;
import sy.rf.demo.repository.FormationRepository;
import sy.rf.demo.service.FormationService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FormationServiceImpl implements FormationService {

    private final FormationRepository formationRepository;

    public FormationServiceImpl(FormationRepository formationRepository) {
        this.formationRepository = formationRepository;
    }

    @Override
    public FormationDto createFormation(FormationDto formationDto) {
        Formation formation = FormationMapper.toEntity(formationDto);
        return FormationMapper.toDto(formationRepository.save(formation));
    }

    @Override
    public FormationDto getFormationById(UUID id) {
        return FormationMapper.toDto(formationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formation non trouvée")));
    }

    @Override
    public Formation getFormationEntityById(UUID id) {
        return formationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formation non trouvée"));
    }

    @Override
    public List<FormationDto> getAllFormations() {
        return formationRepository.findAll().stream()
                .map(FormationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FormationDto updateFormation(FormationDto formationDto) {
        // Vérifier si la formation existe
        formationRepository.findById(formationDto.getId())
                .orElseThrow(() -> new RuntimeException("Formation non trouvée"));

        Formation formation = FormationMapper.toEntity(formationDto);
        return FormationMapper.toDto(formationRepository.save(formation));
    }

    @Override
    public void deleteFormation(UUID id) {
        formationRepository.deleteById(id);
    }
}