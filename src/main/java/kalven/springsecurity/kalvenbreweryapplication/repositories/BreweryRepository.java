package kalven.springsecurity.kalvenbreweryapplication.repositories;

import kalven.springsecurity.kalvenbreweryapplication.domain.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */
public interface BreweryRepository extends JpaRepository<Brewery, UUID> {
}
