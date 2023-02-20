package kalven.springsecurity.kalvenbreweryapplication.service;

import kalven.springsecurity.kalvenbreweryapplication.domain.Brewery;

import java.util.List;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */
public interface BreweryService {
    List<Brewery> getAllBreweries();
}
