package sy.rf.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sy.rf.demo.entity.Domaine;

import java.util.UUID;

public interface DomaineRepository extends JpaRepository<Domaine, UUID> {
}
