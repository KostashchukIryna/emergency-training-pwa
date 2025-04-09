package edu.emergencytrainingpwa.config;

import edu.emergencytrainingpwa.security.jwt.JwtTool;
import edu.emergencytrainingpwa.security.providers.JWTAuthenticationProvider;
import edu.emergencytrainingpwa.service.user.UserService;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTool jwtTool;
    private final UserService userService;
    private final AuthenticationConfiguration authenticationConfiguration;

    @Value("${spring.messaging.stomp.websocket.allowed-origins}")
    private String[] allowedOrigins;

    /**
     * Bean {@link PasswordEncoder} that uses in coding password.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Method for configure security.
     *
     */
    @SuppressWarnings("java:S4502")
//    треба допрацювати
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
//                CorsConfiguration config = new CorsConfiguration();
//                config.setAllowedOriginPatterns(List.of(allowedOrigins));
//                config.setAllowedMethods(
//                    Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
//                config.setAllowedHeaders(
//                    Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Headers",
//                        "X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
//                config.setAllowCredentials(true);
//                config.setMaxAge(3600L);
//                return config;
//            })).csrf(AbstractHttpConfigurer::disable)
//            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
//            .addFilterBefore(new AccessTokenAuthenticationFilter(jwtTool, authenticationManager(), userService),
//                UsernamePasswordAuthenticationFilter.class)
//            .exceptionHandling(exception -> exception.authenticationEntryPoint((req, resp, exc) -> resp
//                    .sendError(SC_UNAUTHORIZED, "Authorize first."))
//                .accessDeniedHandler((req, resp, exc) -> resp.sendError(SC_FORBIDDEN, "You don't have authorities.")))
//            .authorizeHttpRequests(req -> req
//                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .requestMatchers("/error").permitAll()
//        return http.build();
//    }

    /**
     * Method for configure type of authentication provider.
     *
     * @param auth {@link AuthenticationManagerBuilder}
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(new JWTAuthenticationProvider(jwtTool));
    }

    /**
     * Provides AuthenticationManager.
     *
     * @return {@link AuthenticationManager}
     */
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
