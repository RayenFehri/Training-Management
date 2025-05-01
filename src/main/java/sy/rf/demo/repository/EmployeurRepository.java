package sy.rf.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sy.rf.demo.entity.Domaine;
import sy.rf.demo.entity.Employeur;

import java.util.UUID;

public interface EmployeurRepository extends JpaRepository<Employeur, Long> {
}
