package kalven.springsecurity.kalvenbreweryapplication.web.controllers;

import kalven.springsecurity.kalvenbreweryapplication.domain.Brewery;
import kalven.springsecurity.kalvenbreweryapplication.service.BreweryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/brewery")
public class BreweryController {
    private final BreweryService breweryService;

    @GetMapping({"/breweries", "/breweries/index", "/breweries/index.html", "/breweries.html"})
    public String listBreweries(Model model) {
        model.addAttribute("breweries", breweryService.getAllBreweries());
        return "breweries/index";
    }
    @GetMapping("/api/v1/breweries")
    public @ResponseBody
    List<Brewery> getBreweriesJson(){
        return breweryService.getAllBreweries();
    }
}
