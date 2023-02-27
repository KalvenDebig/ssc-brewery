package guru.sfg.brewery.security;

import guru.sfg.brewery.domain.security.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Project ssc-brewery
 * @Author kalvens on 2/27/23
 */

@Slf4j
@Component
public class BeerOrderAuthenticationManager {
    public boolean customerIdMatches(Authentication authentication, UUID customerId) {
        User authenticatedUser = (User) authentication.getPrincipal();
        log.debug("Authenticated User Customer Id: " + authenticatedUser.getCustomer().getId() +
                "Customer Id: " + customerId);

        return customerId.equals(authenticatedUser.getCustomer().getId());
    }
}
