package sy.rf.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sy.rf.demo.entity.Employeur;
import sy.rf.demo.entity.Role;
import sy.rf.demo.entity.User;
import sy.rf.demo.repository.EmployeurRepository;
import sy.rf.demo.repository.RoleRepository;
import sy.rf.demo.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeurServiceImpl implements EmployeurService {

    @Autowired
    private EmployeurRepository employeurRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Employeur> getAllEmployeurs() {
        return employeurRepository.findAll();
    }

    @Override
    public Optional<Employeur> getEmployeurById(UUID id) {
        return employeurRepository.findById(id);
    }

    @Override
    public Employeur createEmployeur(Employeur employeur) {
        // Vérifier si le rôle existe
        Optional<Role> roleOpt = roleRepository.findById(employeur.getUser().getRole().getId());
        if (roleOpt.isEmpty()) {
            throw new RuntimeException("Role not found");
        }

        // Créer un nouvel utilisateur
        User user = employeur.getUser();
        user.setId(UUID.randomUUID()); // Générer un UUID unique
        user.setRole(roleOpt.get());
        userRepository.save(user);

        // Associer l'utilisateur à l'employeur
        employeur.setId(user.getId());
        employeur.setUser(user);

        return employeurRepository.save(employeur);
    }

    @Override
    public Employeur updateEmployeur(UUID id, Employeur employeurDetails) {
        return employeurRepository.findById(id).map(existingEmployeur -> {
            // Mettre à jour les infos de l'employeur
            existingEmployeur.setNomemployeur(employeurDetails.getNomemployeur());

            // Mettre à jour les infos de l'utilisateur associé
            User user = existingEmployeur.getUser();
            user.setEmail(employeurDetails.getUser().getEmail());
            user.setPassword(employeurDetails.getUser().getPassword()); // ⚠️ Hacher le mot de passe en prod

            userRepository.save(user);
            return employeurRepository.save(existingEmployeur);
        }).orElseThrow(() -> new RuntimeException("Employeur not found"));
    }

    @Override
    public void deleteEmployeur(UUID id) {
        employeurRepository.findById(id).ifPresent(employeur -> {
            userRepository.delete(employeur.getUser());
            employeurRepository.delete(employeur);
        });
    }
}
