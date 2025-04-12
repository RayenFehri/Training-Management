package sy.rf.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sy.rf.demo.entity.Employeur;
import sy.rf.demo.repository.EmployeurRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeurServiceImpl implements EmployeurService {

    @Autowired
    EmployeurRepository employeurRepository;

    @Override
    public Employeur createEmployeur(Employeur employeur) {
        return employeurRepository.save(employeur);
    }

    @Override
    public Employeur getEmployeurById(UUID id) {
        return employeurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employeur non trouvé"));
    }

    @Override
    public List<Employeur> getAllEmployeurs() {
        return employeurRepository.findAll();
    }

    @Override
    public Employeur updateEmployeur(UUID id, Employeur updatedEmployeur) {
        Employeur employeur = getEmployeurById(id);
        employeur.setNomemployeur(updatedEmployeur.getNomemployeur());
        return employeurRepository.save(employeur);
    }

    @Override
    public void deleteEmployeur(UUID id) {
        employeurRepository.deleteById(id);
    }
}
