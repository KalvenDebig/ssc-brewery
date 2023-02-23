package guru.sfg.brewery.repositories.security;

import guru.sfg.brewery.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @Project ssc-brewery
 * @Author kalvens on 2/23/23
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String username);
}
