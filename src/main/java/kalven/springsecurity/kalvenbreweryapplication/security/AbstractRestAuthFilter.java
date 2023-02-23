package kalven.springsecurity.kalvenbreweryapplication.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.IOException;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/23/23
 */
@Slf4j
public abstract class AbstractRestAuthFilter extends AbstractAuthenticationProcessingFilter {
    public AbstractRestAuthFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
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
        try {
            if (result != null) {
                successfulAuthentication(request, response, chain, result);
            } else {
                chain.doFilter(request, response);
            }
        } catch (AuthenticationException e) {
            log.error("Authentication failed", e);
            unsuccessfulAuthentication(request, response, e);
        }

    }

    /**
     * Rewrite default unsuccessfulAuthentication
     * Clear contexts in the first place
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        if (log.isDebugEnabled()) {
            log.debug("Authentication Request Failed: " + failed.toString());
            log.debug("Update SecurityContextHolder to contain null Authentication");
        }
        response.sendError(HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase());
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

    protected abstract String getUserName(HttpServletRequest request);
    protected abstract String getPassword(HttpServletRequest request);
}
