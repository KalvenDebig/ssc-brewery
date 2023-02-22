package kalven.springsecurity.kalvenbreweryapplication.web.controllers;

import kalven.springsecurity.kalvenbreweryapplication.repositories.BeerInventoryRepository;
import kalven.springsecurity.kalvenbreweryapplication.repositories.BeerRepository;
import kalven.springsecurity.kalvenbreweryapplication.repositories.CustomerRepository;
import kalven.springsecurity.kalvenbreweryapplication.service.BeerService;
import kalven.springsecurity.kalvenbreweryapplication.service.BreweryService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/22/23
 */
public abstract class BaseIT {
    @Autowired
    WebApplicationContext wac;

    protected MockMvc mockMvc;

    @MockBean
    BeerInventoryRepository beerInventoryRepository;
    @MockBean
    BeerRepository beerRepository;
    @MockBean
    BreweryService breweryService;
    @MockBean
    CustomerRepository customerRepository;
    @MockBean
    BeerService beerService;
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }
}
