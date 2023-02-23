package kalven.springsecurity.kalvenbreweryapplication.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/22/23
 */
@Slf4j
public class RestHeaderAuthFilter extends AbstractAuthenticationProcessingFilter {
    public RestHeaderAuthFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (logger.isDebugEnabled()) {
            logger.debug("request is process authentication");
        }

        Authentication result = attemptAuthentication(request, response);

        if (result != null) {
            successfulAuthentication(request, response, chain, result);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String userName = getUserName(request);
        String password = getPassword(request);
        if (userName == null) {
            userName =  "";
        }

        if (password == null) {
            password = "";
        }

        log.debug("Authentication User: " + userName);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, password);


        return this.getAuthenticationManager().authenticate(token);
    }

    private String getPassword(HttpServletRequest request) {
        return request.getHeader("Api-Secret");
    }

    private String getUserName(HttpServletRequest request) {
        return request.getHeader("Api-Key");
    }
}
