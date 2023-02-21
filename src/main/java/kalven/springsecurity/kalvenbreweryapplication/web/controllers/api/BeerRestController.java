package kalven.springsecurity.kalvenbreweryapplication.web.controllers.api;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import kalven.springsecurity.kalvenbreweryapplication.service.BeerService;
import kalven.springsecurity.kalvenbreweryapplication.web.model.BeerDto;
import kalven.springsecurity.kalvenbreweryapplication.web.model.BeerPagedList;
import kalven.springsecurity.kalvenbreweryapplication.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class BeerRestController {
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;
    private final BeerService beerService;

    @GetMapping(produces = {"application/json"}, path = "beer")
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "beerName", required = false) String beerName,
                                                   @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
                                                   @RequestParam(value = "showInventoryOnHand", required = false) Boolean show) {

        log.debug("Listing Beers");
        if (show == null) {
            show = false;
        }

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        BeerPagedList beerList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), show);
        return new ResponseEntity<>(beerList, HttpStatus.OK);
    }

    @GetMapping(path = {"beer/{beerId}"}, produces = {"application/json"})
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId,
                                               @RequestParam(value = "showInventoryOnHand", required = false) Boolean show) {
        log.debug("Get Request for BeerId: " + beerId);
        if(show == null) show = false;

        return new ResponseEntity<>(beerService.findBeerById(beerId, show), HttpStatus.OK);
    }

    @GetMapping(path = {"beerUpc/{upc}"}, produces = { "application/json" })
    public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable("upc") String upc){
        return new ResponseEntity<>(beerService.findBeerByUpc(upc), HttpStatus.OK);
    }

    @PostMapping(path = "beer")
    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto){

        BeerDto savedDto = beerService.saveBeer(beerDto);

        HttpHeaders httpHeaders = new HttpHeaders();

        //todo hostname for uri
        httpHeaders.add("Location", "/api/v1/beer_service/" + savedDto.getId().toString());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping(path = {"beer/{beerId}"}, produces = { "application/json" })
    public ResponseEntity updateBeer(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto){

        beerService.updateBeer(beerId, beerDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"beer/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable("beerId") UUID beerId){
        beerService.deleteById(beerId);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<List> badReqeustHandler(ConstraintViolationException e){
        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());

        e.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath().toString() + " : " + constraintViolation.getMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
