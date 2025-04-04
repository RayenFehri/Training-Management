package sy.rf.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sy.rf.demo.entity.Role;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
