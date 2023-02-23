package kalven.springsecurity.kalvenbreweryapplication.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/23/23
 */
@Slf4j
public class RestUrlAuthFilter extends AbstractRestAuthFilter {
    public RestUrlAuthFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }


    @Override
    protected String getUserName(HttpServletRequest request) {
        return request.getParameter("apiSecret");
    }

    @Override
    protected String getPassword(HttpServletRequest request) {
        return request.getParameter("apiKey");
    }
}
