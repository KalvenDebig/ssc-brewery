package kalven.springsecurity.kalvenbreweryapplication.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/22/23
 */
@Slf4j
public class RestHeaderAuthFilter extends AbstractRestAuthFilter {
    public RestHeaderAuthFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    protected String getPassword(HttpServletRequest request) {
        return request.getHeader("Api-Secret");
    }

    protected String getUserName(HttpServletRequest request) {
        return request.getHeader("Api-Key");
    }
}
