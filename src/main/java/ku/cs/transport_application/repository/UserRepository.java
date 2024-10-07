package ku.cs.transport_application.repository;

import ku.cs.transport_application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
    User findByUserType(String userType);
}
