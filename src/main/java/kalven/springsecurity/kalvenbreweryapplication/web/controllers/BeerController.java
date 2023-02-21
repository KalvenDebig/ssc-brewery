package kalven.springsecurity.kalvenbreweryapplication.web.controllers;

import jakarta.validation.Valid;
import kalven.springsecurity.kalvenbreweryapplication.domain.Beer;
import kalven.springsecurity.kalvenbreweryapplication.repositories.BeerInventoryRepository;
import kalven.springsecurity.kalvenbreweryapplication.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/beers")
public class BeerController {
    private final BeerRepository beerRepository;
    private final BeerInventoryRepository beerInventoryRepository;

    @RequestMapping("/find")
    public String findBeers(Model model) {
        model.addAttribute("beer", Beer.builder());
        return "beers/findBeers";
    }
    private PageRequest createPageRequest(int page, int size, Sort.Direction sortDirection, String propertyName) {
        return PageRequest.of(page, size, Sort.by(sortDirection, propertyName));
    }

    @GetMapping
    public String processFindFormReturnMany(Beer beer, BindingResult result, Model model) {
        // find beer by name
        // ToDO: add service
        // ToDO: get paging data from view
        Page<Beer> pagedResult = beerRepository.findAllByBeerNameIsLike("%" + beer.getBeerName() + "%",
                createPageRequest(0, 10, Sort.Direction.DESC, "beerName"));
        List<Beer> beerList = pagedResult.getContent();
        if (beerList.isEmpty()) {
            result.rejectValue("beerName", "notFound", "not found");
            return "beers/findBeers";
        } else if (beerList.size() == 1) {
            beer = beerList.get(0);
            return "redirect:/beers/" + beer.getId();
        }
        model.addAttribute("selections", beerList);
        return "beers/beerList";
    }

    @GetMapping("/{beerId}")
    public ModelAndView showBeer(@PathVariable UUID beerId) {
        ModelAndView mav = new ModelAndView("beers/beerDetails");
        mav.addObject(beerRepository.findById(beerId).get());

        return mav;
    }
    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("beer", Beer.builder().build());
        return "beers/createBeer";
    }

    @PostMapping("/new")
    public String processCreationForm(Beer beer) {
        //ToDO: Add Service
        Beer newBeer = Beer.builder()
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .minOnHand(beer.getMinOnHand())
                .price(beer.getPrice())
                .quantityToBrew(beer.getQuantityToBrew())
                .upc(beer.getUpc())
                .build();

        Beer savedBeer = beerRepository.save(newBeer);
        return "redirect:/beers/" + savedBeer.getId();
    }

    @GetMapping("/{beerId}/edit")
    public String initUpdateBeerForm(@PathVariable UUID beerId, Model model) {
        if (beerRepository.findById(beerId).isPresent())
            model.addAttribute("beer", beerRepository.findById(beerId).get());
        return "beers/createOrUpdateBeer";
    }

    @PostMapping("/{beerId}/edit")
    public String processUpdateForm(@Valid Beer beer, BindingResult result) {
        if (result.hasErrors()) {
            return "beers/createOrUpdateBeer";
        } else {
            //ToDO: Add Service
            Beer savedBeer = beerRepository.save(beer);
            return "redirect:/beers/" + savedBeer.getId();
        }
    }
}
