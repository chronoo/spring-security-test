package security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import security.entity.SimpleUser;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SimpleUser, Integer> {
    Optional<SimpleUser> findByUsername(String username);
}
