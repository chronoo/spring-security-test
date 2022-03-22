package security.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import security.db.entity.SimpleUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SimpleUser, Integer> {
    Optional<SimpleUser> findByUsername(String username);
}
