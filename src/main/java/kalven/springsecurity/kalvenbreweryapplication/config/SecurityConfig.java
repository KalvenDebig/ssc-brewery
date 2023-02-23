package kalven.springsecurity.kalvenbreweryapplication.config;

import kalven.springsecurity.kalvenbreweryapplication.security.KalvenPasswordEncoderFactory;
import kalven.springsecurity.kalvenbreweryapplication.security.RestHeaderAuthFilter;
import kalven.springsecurity.kalvenbreweryapplication.security.RestUrlAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/22/23
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    public RestHeaderAuthFilter restHeaderAuthFilter(AuthenticationManager authenticationManager) {
        RestHeaderAuthFilter filter = new RestHeaderAuthFilter(new AntPathRequestMatcher("/api/**"));
        filter.setAuthenticationManager(authenticationManager);

        return filter;
    }

    public RestUrlAuthFilter restUrlAuthFilter(AuthenticationManager authenticationManager) {
        RestUrlAuthFilter filter = new RestUrlAuthFilter(new AntPathRequestMatcher("/api/**"));
        filter.setAuthenticationManager(authenticationManager);

        return filter;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return KalvenPasswordEncoderFactory.createDelegatingPasswordEncoder("bcrypt15");
    }

    /**
     *
     * @param http, takes in a HttpSecurity Object, we can use matchers to verify mapping, and give the request permission
     * @return SecurityFilterCharin, make this a part of Filter Chain
     * @throws Exception
     */
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService());
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        http.addFilterBefore(restHeaderAuthFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();

        http.addFilterBefore(restUrlAuthFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests(authorize -> {
                    authorize
                            .requestMatchers("/", "/webjars/**", "/login", "/resources/**").permitAll()
                            .requestMatchers("/beers/find", "/beers*").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/beer/**").permitAll()
                            .requestMatchers(HttpMethod.GET, "/api/v1/beerUpc/**").permitAll();
                } )
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic();

        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin_user")
                .password("admin_user_password")
                .roles("ADMIN")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user_user")
                .password("user_user_password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}