package sy.rf.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sy.rf.demo.entity.User;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
