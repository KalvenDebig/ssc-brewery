package kalven.springsecurity.kalvenbreweryapplication.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */
@Controller
public class IndexController {
    @GetMapping({"", "/"})
    public String index() {
        return "index";
    }
}
