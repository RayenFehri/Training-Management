package sy.rf.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sy.rf.demo.entity.Participant;

import java.util.UUID;

public interface ParticipantRepository extends JpaRepository<Participant, UUID> {
}
