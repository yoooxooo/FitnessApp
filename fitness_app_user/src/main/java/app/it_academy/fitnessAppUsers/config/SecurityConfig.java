package app.it_academy.fitnessAppUsers.config;

import app.it_academy.fitnessAppUsers.filter.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter filter;

    public SecurityConfig(JwtFilter filter) {
        this.filter = filter;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
        http = http.cors().and().csrf().disable();

        // Set session management to stateless
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set unauthorized requests exception handler
        http = http
                .exceptionHandling()
                .accessDeniedHandler(
                        (request, response, ex) -> {
                            response.setStatus(
                                    HttpServletResponse.SC_FORBIDDEN
                            );
                        }
                )
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.setStatus(
                                    HttpServletResponse.SC_UNAUTHORIZED
                            );
                        }
                )
                .and();

        // Set permissions on endpoints
        http.authorizeHttpRequests((requests) -> requests
                // Our public endpoints
                .requestMatchers("/users/registration", "/users/login", "/users/verification").permitAll()
                // Our private endpoints
                .requestMatchers("/users/me").authenticated()
                .requestMatchers("/users/**").hasRole("ADMIN")
                .anyRequest().authenticated());

        // Add JWT token filter
        http.addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }
}