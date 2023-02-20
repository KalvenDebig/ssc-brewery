package kalven.springsecurity.kalvenbreweryapplication.web.mappers;

import kalven.springsecurity.kalvenbreweryapplication.domain.Beer;
import kalven.springsecurity.kalvenbreweryapplication.domain.BeerInventory;
import kalven.springsecurity.kalvenbreweryapplication.web.model.BeerDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */

@AllArgsConstructor
public abstract class BeerMapperDecorator implements BeerMapper {
    private final BeerMapper beerMapper;

    @Override
    public BeerDto beerToBeerDto(Beer beer) {
        BeerDto dto = beerMapper.beerToBeerDto(beer);
        if (beer.getBeerInventory() != null && beer.getBeerInventory().size() > 0) {
            dto.setQuantityOnHand(beer.getBeerInventory()
                    .stream()
                    .map(BeerInventory::getQuantityOnHand)
                    .reduce(0, Integer::sum));
        }

        return dto;
    }
}
