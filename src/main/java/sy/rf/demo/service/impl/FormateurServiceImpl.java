package sy.rf.demo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sy.rf.demo.dto.FormateurDto;
import sy.rf.demo.entity.Formateur;
import sy.rf.demo.mappers.EmployeurMapper;
import sy.rf.demo.mappers.FormateurMapper;
import sy.rf.demo.mappers.UserMapper;
import sy.rf.demo.repository.FormateurRepository;
import sy.rf.demo.service.FormateurService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FormateurServiceImpl implements FormateurService {


    private final FormateurRepository formateurRepository;
public FormateurServiceImpl(FormateurRepository formateurRepository) {
    this.formateurRepository = formateurRepository;
}
    @Override
    public FormateurDto createFormateur(FormateurDto formateurDto) {
        Formateur formateur = Formateur.builder()
                .id(formateurDto.getId())
                .nom(formateurDto.getNom())
                .prenom(formateurDto.getPrenom())
                .email(formateurDto.getEmail())
                .tel(formateurDto.getTel())
                .type(formateurDto.getType())
                .employeur(EmployeurMapper.toEntity(formateurDto.getEmployeur()))
                .user(UserMapper.toEntity(formateurDto.getUser()))
                .build();
        return FormateurMapper.toDto(formateurRepository.save(formateur));
    }

    @Override
    public FormateurDto getFormateurById(Long id) {
        return FormateurMapper.toDto(formateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formateur non trouvé")));
    }

    @Override
    public List<FormateurDto> getAllFormateurs() {
        return formateurRepository.findAll().stream().map(FormateurMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public FormateurDto updateFormateur( FormateurDto updatedFormateur) {
    if(!formateurRepository.existsById(updatedFormateur.getId())) {
        throw new RuntimeException("Formateur do not exist");
    }
    Formateur formateur = Formateur.builder()
            .id(updatedFormateur.getId())
            .nom(updatedFormateur.getNom())
            .prenom(updatedFormateur.getPrenom())
            .email(updatedFormateur.getEmail())
            .tel(updatedFormateur.getTel())
            .type(updatedFormateur.getType())
            .employeur(EmployeurMapper.toEntity(updatedFormateur.getEmployeur()))
            .user(UserMapper.toEntity(updatedFormateur.getUser()))
            .build();
        return FormateurMapper.toDto(formateurRepository.save(formateur));
    }

    @Override
    public void deleteFormateur(Long id) {
        formateurRepository.deleteById(id);
    }
}
