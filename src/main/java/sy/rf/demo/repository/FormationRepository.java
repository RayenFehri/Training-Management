package sy.rf.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sy.rf.demo.entity.Formation;

import java.util.UUID;

public interface FormationRepository extends JpaRepository<Formation, UUID> {
}
