package sy.rf.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sy.rf.demo.entity.Profil;

import java.util.UUID;

public interface ProfilRepository extends JpaRepository<Profil, UUID> {
}
