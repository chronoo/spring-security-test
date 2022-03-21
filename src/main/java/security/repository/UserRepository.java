package security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import security.entity.SimpleUser;

public interface UserRepository extends JpaRepository<SimpleUser, Integer> {
    SimpleUser findByUsername(String username);
}
