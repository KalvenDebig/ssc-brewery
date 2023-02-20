package kalven.springsecurity.kalvenbreweryapplication.service;

import kalven.springsecurity.kalvenbreweryapplication.domain.Brewery;
import kalven.springsecurity.kalvenbreweryapplication.repositories.BreweryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */
@Service
@RequiredArgsConstructor
public class BreweryServiceImpl implements BreweryService {
    private final BreweryRepository breweryRepository;

    @Override
    public List<Brewery> getAllBreweries() {
        return breweryRepository.findAll();
    }
}
