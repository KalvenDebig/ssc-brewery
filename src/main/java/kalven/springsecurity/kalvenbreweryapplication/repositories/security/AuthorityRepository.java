package kalven.springsecurity.kalvenbreweryapplication.repositories.security;

import kalven.springsecurity.kalvenbreweryapplication.domain.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/23/23
 */
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
