package kalven.springsecurity.kalvenbreweryapplication.config;

import kalven.springsecurity.kalvenbreweryapplication.security.KalvenPasswordEncoderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/22/23
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return KalvenPasswordEncoderFactory.createDelegatingPasswordEncoder("bcrypt15");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
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