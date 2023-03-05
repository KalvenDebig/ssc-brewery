package guru.sfg.brewery.repositories.security;

import guru.sfg.brewery.domain.security.LoginSuccess;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Project ssc-brewery
 * @Author kalvens on 3/5/23
 */

public interface LoginSuccessRepository extends JpaRepository<LoginSuccess, Integer> {
}
