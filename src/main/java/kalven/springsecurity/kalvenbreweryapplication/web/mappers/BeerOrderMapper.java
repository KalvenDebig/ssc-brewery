package kalven.springsecurity.kalvenbreweryapplication.web.mappers;

import kalven.springsecurity.kalvenbreweryapplication.domain.BeerOrder;
import kalven.springsecurity.kalvenbreweryapplication.web.model.BeerOrderDto;
import org.mapstruct.Mapper;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */
@Mapper(uses = {DateMapper.class, BeerOrderLineMapper.class})
public interface BeerOrderMapper {
    BeerOrderDto beerOrderToDto(BeerOrder beerOrder);
    BeerOrder dtoToBeerOrder(BeerOrderDto beerOrderDto);
}
