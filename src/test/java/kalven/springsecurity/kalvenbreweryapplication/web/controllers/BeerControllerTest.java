package kalven.springsecurity.kalvenbreweryapplication.web.controllers;

import kalven.springsecurity.kalvenbreweryapplication.domain.Beer;
import kalven.springsecurity.kalvenbreweryapplication.repositories.BeerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.data.repository.util.ClassUtils.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */
@ExtendWith(MockitoExtension.class)
class BeerControllerTest {
    @Mock
    BeerRepository beerRepository;

    @InjectMocks
    BeerController beerController;

    List<Beer> beerList;
    UUID uuid;
    Beer beer;

    MockMvc mockMvc;
    Page<Beer> beers;
    Page<Beer> pagedResponse;

    @BeforeEach
    void setUp() {
        beerList = new ArrayList<>();
        beerList.add(Beer.builder().build());
        beerList.add(Beer.builder().build());
        pagedResponse = new PageImpl<>(beerList);
        final String id = "493410b3-dd0b-4b78-97bf-289f50f6e74f";
        uuid = UUID.fromString(id);

        mockMvc = MockMvcBuilders.standaloneSetup(beerController).build();
    }
}