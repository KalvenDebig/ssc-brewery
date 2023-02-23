package guru.sfg.brewery.repositories.security;

import guru.sfg.brewery.domain.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Project ssc-brewery
 * @Author kalvens on 2/23/23
 */
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
