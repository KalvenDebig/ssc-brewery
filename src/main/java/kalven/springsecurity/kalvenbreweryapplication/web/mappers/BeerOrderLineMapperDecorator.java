package kalven.springsecurity.kalvenbreweryapplication.web.mappers;

import kalven.springsecurity.kalvenbreweryapplication.domain.BeerOrderLine;
import kalven.springsecurity.kalvenbreweryapplication.repositories.BeerRepository;
import kalven.springsecurity.kalvenbreweryapplication.web.model.BeerOrderLineDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */
public abstract class BeerOrderLineMapperDecorator implements BeerOrderLineMapper {
    private BeerRepository beerRepository;
    private BeerOrderLineMapper beerOrderLineMapper;

    @Autowired
    public void setBeerRepository(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Autowired
    @Qualifier("delegate")
    public void setBeerOrderLineMapper(BeerOrderLineMapper beerOrderLineMapper) {
        this.beerOrderLineMapper = beerOrderLineMapper;
    }

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
