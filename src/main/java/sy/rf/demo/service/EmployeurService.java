package sy.rf.demo.service;

import sy.rf.demo.entity.Employeur;

import java.util.List;
import java.util.UUID;

public interface EmployeurService {
    Employeur createEmployeur(Employeur employeur);
    Employeur getEmployeurById(UUID id);
    List<Employeur> getAllEmployeurs();
    Employeur updateEmployeur(UUID id, Employeur employeur);
    void deleteEmployeur(UUID id);
}
