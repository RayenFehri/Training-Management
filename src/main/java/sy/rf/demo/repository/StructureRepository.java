package sy.rf.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sy.rf.demo.entity.Structure;

import java.util.UUID;

public interface StructureRepository extends JpaRepository<Structure, UUID> {
}
