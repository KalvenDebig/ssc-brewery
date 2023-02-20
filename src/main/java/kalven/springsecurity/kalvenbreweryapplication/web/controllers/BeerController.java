package kalven.springsecurity.kalvenbreweryapplication.web.controllers;

import kalven.springsecurity.kalvenbreweryapplication.domain.Beer;
import kalven.springsecurity.kalvenbreweryapplication.repositories.BeerInventoryRepository;
import kalven.springsecurity.kalvenbreweryapplication.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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


}
