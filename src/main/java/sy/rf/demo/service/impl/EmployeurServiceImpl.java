package sy.rf.demo.service.impl;

import org.springframework.stereotype.Service;
import sy.rf.demo.dto.EmployeurDto;
import sy.rf.demo.mappers.EmployeurMapper;
import sy.rf.demo.repository.EmployeurRepository;
import sy.rf.demo.service.EmployeurService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeurServiceImpl implements EmployeurService {
    private final EmployeurRepository employeurRepository;
    public EmployeurServiceImpl(EmployeurRepository employeurRepository) {
        this.employeurRepository = employeurRepository;
    }
    @Override
    public EmployeurDto createEmployeur(EmployeurDto employeur) {
        return EmployeurMapper.toDto(employeurRepository.save(EmployeurMapper.toEntity(employeur)));
    }

    @Override
    public EmployeurDto getEmployeurById(Long id) {
        return EmployeurMapper.toDto(employeurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employeur non trouvé")));
    }

    @Override
    public List<EmployeurDto> getAllEmployeurs() {
        return employeurRepository.findAll().stream().map(EmployeurMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public EmployeurDto updateEmployeur( EmployeurDto updatedEmployeur) {
        if(!employeurRepository.existsById(updatedEmployeur.getId())) {
            throw new RuntimeException("Employeur do not exist");
        }
        return EmployeurMapper.toDto( employeurRepository.save(EmployeurMapper.toEntity(updatedEmployeur)));
    }

    @Override
    public void deleteEmployeur(Long id) {
        employeurRepository.deleteById(id);
    }
}
