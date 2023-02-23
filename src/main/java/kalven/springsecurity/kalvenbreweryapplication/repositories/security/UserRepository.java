package kalven.springsecurity.kalvenbreweryapplication.repositories.security;

import kalven.springsecurity.kalvenbreweryapplication.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/23/23
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String username);
}
