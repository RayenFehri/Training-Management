package sy.rf.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sy.rf.demo.entity.Employeur;
import sy.rf.demo.entity.Formateur;

import java.util.UUID;

public interface FormateurRepository extends JpaRepository<Formateur, UUID> {
}
