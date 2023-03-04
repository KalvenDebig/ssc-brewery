package guru.sfg.brewery.security.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * @Project ssc-brewery
 * @Author kalvens on 3/4/23
 */
@Slf4j
@Component
public class AuthenticationSuccessListener {
    @EventListener
    public void listen(AuthenticationSuccessEvent event) {
        log.debug("User Logged In");
    }
}
