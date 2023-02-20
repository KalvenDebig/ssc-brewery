package kalven.springsecurity.kalvenbreweryapplication.web.mappers;

import kalven.springsecurity.kalvenbreweryapplication.domain.BeerOrderLine;
import kalven.springsecurity.kalvenbreweryapplication.web.model.BeerOrderLineDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */
@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerOrderLineMapperDecorator.class)
public interface BeerOrderLineMapper {
    BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line);

    BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto);
}
