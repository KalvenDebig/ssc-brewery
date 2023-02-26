package guru.sfg.brewery.repositories.security;

import guru.sfg.brewery.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Project ssc-brewery
 * @Author kalvens on 2/25/23
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
