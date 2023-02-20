package kalven.springsecurity.kalvenbreweryapplication.web.mappers;

import kalven.springsecurity.kalvenbreweryapplication.domain.BeerOrderLine;
import kalven.springsecurity.kalvenbreweryapplication.repositories.BeerRepository;
import kalven.springsecurity.kalvenbreweryapplication.web.model.BeerOrderLineDto;
import lombok.AllArgsConstructor;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */
@AllArgsConstructor
public abstract class BeerOrderLineMapperDecorator implements BeerOrderLineMapper {
    private final BeerRepository beerRepository;
    private final BeerOrderLineMapper beerOrderLineMapper;

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        BeerOrderLineDto dto = beerOrderLineMapper.beerOrderLineToDto(line);
        dto.setBeerId(line.getBeer().getId());
        return dto;
    }

    @Override
    public BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto) {
        BeerOrderLine line = beerOrderLineMapper.dtoToBeerOrderLine(dto);
        line.setBeer(beerRepository.getReferenceById(dto.getBeerId()));
        line.setQuantityAllocated(0);
        return line;
    }
}
