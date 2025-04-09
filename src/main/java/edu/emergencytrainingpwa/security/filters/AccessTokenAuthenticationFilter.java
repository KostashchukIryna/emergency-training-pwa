package edu.emergencytrainingpwa.security.filters;

import edu.emergencytrainingpwa.dto.user.UserDto;
import edu.emergencytrainingpwa.security.jwt.JwtTool;
import edu.emergencytrainingpwa.service.user.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class AccessTokenAuthenticationFilter  extends OncePerRequestFilter {
    private final JwtTool jwtTool;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    private String getTokenFromCookies(Cookie[] cookies) {
        return Arrays.stream(cookies)
            .filter(c -> c.getName().equals("accessToken"))
            .findFirst()
            .map(Cookie::getValue).orElse(null);
    }

    private String extractToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String uri = request.getRequestURI();
        if (cookies != null && uri.startsWith("/management")) {
            return getTokenFromCookies(cookies);
        }

        return jwtTool.getTokenFromHttpServletRequest(request);
    }

    @Override
    public void doFilterInternal(
        @SuppressWarnings("NullableProblems") HttpServletRequest request,
        @SuppressWarnings("NullableProblems") HttpServletResponse response,
        @SuppressWarnings("NullableProblems") FilterChain chain)
        throws IOException, ServletException {
        String token = extractToken(request);

        if (token != null) {
            try {
                Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(token, null));
                Optional<UserDto> user = userService.findByEmail((String) authentication.getPrincipal());
                if (user.isPresent()) {
                    log.debug("User successfully authenticate - {}", authentication.getPrincipal());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (ExpiredJwtException e) {
                log.info("Token has expired: {}", token);
            } catch (Exception e) {
                log.info("Access denied with token: {}", e.getMessage());
            }
        }
        chain.doFilter(request, response);
    }
}
