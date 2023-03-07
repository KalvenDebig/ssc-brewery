package guru.sfg.brewery.web.controllers.api;

import guru.sfg.brewery.web.controllers.BaseIT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Project ssc-brewery
 * @Author kalvens on 3/7/23
 */

@SpringBootTest
public class CorsIT extends BaseIT {
    @WithUserDetails("spring")
    @Test
    void findBeersAUTH() throws Exception {
        mockMvc.perform(get("/api/v1/beer/")
                .header("Origin", "https://springframework.guru"))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"));
    }

    @Test
    void findBeersPREFILGHT() throws Exception {
        mockMvc.perform(options("/api/v1/beer/")
                .header("Origin", "https://springframework.guru")
                        .header("Access-Control-Request-Method", "GET"))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"));
    }

    @Test
    void postBeersPREFILGHT() throws Exception {
        mockMvc.perform(options("/api/v1/beer/")
                .header("Origin", "https://springframework.guru")
                .header("Access-Control-Request-Method", "POST"))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"));
    }

    @Test
    void putBeersPREFILGHT() throws Exception {
        mockMvc.perform(options("/api/v1/beer/1234")
                .header("Origin", "https://springframework.guru")
                .header("Access-Control-Request-Method", "PUT"))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"));
    }

    @Test
    void deleteBeersPREFLIGHT() throws Exception {
        mockMvc.perform(options("/api/v1/beer/1234")
                .header("Origin", "https://springframework.guru")
                .header("Access-Control-Request-Method", "DELETE"))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "*"));
    }
}
