package sy.rf.demo.service;

import sy.rf.demo.dto.EmployeurDto;
import sy.rf.demo.entity.Employeur;

import java.util.List;
import java.util.UUID;

public interface EmployeurService {
    EmployeurDto createEmployeur(EmployeurDto employeur);
    EmployeurDto getEmployeurById(Long id);
    List<EmployeurDto> getAllEmployeurs();
    EmployeurDto updateEmployeur( EmployeurDto employeur);
    void deleteEmployeur(Long id);
}
