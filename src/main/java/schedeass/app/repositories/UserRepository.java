package schedeass.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import schedeass.app.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);


}
