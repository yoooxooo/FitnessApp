package app.it_academy.fitnessAppUsers.filter;

import app.it_academy.fitnessAppUsers.core.dto.userDto.UserDetailsUuid;
import app.it_academy.fitnessAppUsers.domain.User;
import app.it_academy.fitnessAppUsers.mappers.UserMapper;
import app.it_academy.fitnessAppUsers.service.UserDetailsService;
import app.it_academy.fitnessAppUsers.utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailsService userManager;

    private final UserMapper mapper;

    public JwtFilter(UserDetailsService userManager, UserMapper userMapper) {
        this.userManager = userManager;
        this.mapper = userMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // Get jwt token and validate
        final String token = header.split(" ")[1].trim();
        if (!JwtTokenUtil.validate(token)) {
            chain.doFilter(request, response);
            return;
        }

        // Get user identity and set it on the spring security context
        Optional<User> optionalUser = userManager.findUserByUsername(JwtTokenUtil.getUsername(token));
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserDetailsUuid userDetails = mapper.getUserDetails(user);

            UsernamePasswordAuthenticationToken
                    authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null,
                    userDetails.getAuthorities()
            );

            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        }
    }
}