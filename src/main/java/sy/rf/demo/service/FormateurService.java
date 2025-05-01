package sy.rf.demo.service;

import sy.rf.demo.dto.FormateurDto;
import sy.rf.demo.entity.Formateur;

import java.util.List;
import java.util.UUID;

public interface FormateurService {
    FormateurDto createFormateur(FormateurDto formateur);
    FormateurDto getFormateurById(Long id);
    List<FormateurDto> getAllFormateurs();
    FormateurDto updateFormateur( FormateurDto formateur);
    void deleteFormateur(Long id);
}
