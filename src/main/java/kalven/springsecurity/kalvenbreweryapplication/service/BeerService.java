package kalven.springsecurity.kalvenbreweryapplication.service;

import kalven.springsecurity.kalvenbreweryapplication.web.model.BeerDto;
import kalven.springsecurity.kalvenbreweryapplication.web.model.BeerPagedList;
import kalven.springsecurity.kalvenbreweryapplication.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */
public interface BeerService {
    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand);
    BeerDto findBeerById(UUID beerId, Boolean showInventoryOnHand);
    BeerDto saveBeer(BeerDto beerDto);
    void updateBeer(UUID beerId, BeerDto beerDto);
    void deleteById(UUID beerId);
    BeerDto findBeerByUpc(String upc);
}
