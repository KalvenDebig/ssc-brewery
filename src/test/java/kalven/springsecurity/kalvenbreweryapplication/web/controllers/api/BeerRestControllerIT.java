package kalven.springsecurity.kalvenbreweryapplication.web.controllers.api;

import kalven.springsecurity.kalvenbreweryapplication.web.controllers.BaseIT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/22/23
 */
@WebMvcTest
class BeerRestControllerIT extends BaseIT {

    @Test
    void listBeers() throws Exception {
        mockMvc.perform(get("/api/v1/beer"))
                .andExpect(status().isOk());
    }

//    @Test
//    void getBeerById() throws Exception {
//        mockMvc.perform(get("/api/v1/beer/e0182c80-f328-4ed7-8506-d61d23683575"))
//                .andExpect(status().isOk());
//    }
}