package kalven.springsecurity.kalvenbreweryapplication.service;

import kalven.springsecurity.kalvenbreweryapplication.domain.Beer;
import kalven.springsecurity.kalvenbreweryapplication.repositories.BeerRepository;
import kalven.springsecurity.kalvenbreweryapplication.web.mappers.BeerMapper;
import kalven.springsecurity.kalvenbreweryapplication.web.model.BeerDto;
import kalven.springsecurity.kalvenbreweryapplication.web.model.BeerPagedList;
import kalven.springsecurity.kalvenbreweryapplication.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {
        BeerPagedList beerPagedList;
        Page<Beer> beerPage;
        if (beerName != null && !StringUtils.trimAllWhitespace(beerName).isEmpty()
                && beerStyle != null && !StringUtils.trimAllWhitespace(String.valueOf(beerStyle)).isEmpty()) {
            log.debug("Search by Both");
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (beerName != null && !StringUtils.trimAllWhitespace(beerName).isEmpty()
                && (beerStyle == null || StringUtils.trimAllWhitespace(String.valueOf(beerStyle)).isEmpty())) {
            log.debug("Search by Name");
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if ((beerName == null || StringUtils.trimAllWhitespace(beerName).isEmpty())
                && beerStyle != null && !StringUtils.trimAllWhitespace(String.valueOf(beerStyle)).isEmpty()) {
            log.debug("Search by Style");
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            log.debug("Search all");
            beerPage = beerRepository.findAll(pageRequest);
        }

        beerPagedList = new BeerPagedList(beerPage.getContent()
                .stream()
                .map(beerMapper::beerToBeerDto)
                .collect(Collectors.toList()),
                PageRequest.of(beerPage.getPageable().getPageNumber(),
                        beerPage.getPageable().getPageSize()), beerPage.getTotalElements());

        return beerPagedList;
    }

    @Override
    public BeerDto findBeerById(UUID beerId, Boolean showInventoryOnHand) {
        Optional<Beer> beerOptional = beerRepository.findById(beerId);
        if (beerOptional.isPresent()) {
            log.debug("Found BeerId: " + beerId);
            return beerMapper.beerToBeerDto(beerOptional.get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found. UUID: " + beerId);
    }

    @Override
    public BeerDto saveBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        Optional<Beer> beerOptional = beerRepository.findById(beerId);
        beerOptional.ifPresentOrElse(beer -> {
            beer.setBeerName(beerDto.getBeerName());
            beer.setBeerStyle(beerDto.getBeerStyle());
            beer.setPrice(beerDto.getPrice());
            beer.setUpc(beerDto.getUpc());

            beerRepository.save(beer);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found. UUID: " + beerId);
        });
    }

    @Override
    public void deleteById(UUID beerId) {
        beerRepository.deleteById(beerId);
    }

    @Override
    public BeerDto findBeerByUpc(String upc) {
        return beerMapper.beerToBeerDto(beerRepository.findByUpc(upc));
    }
}
