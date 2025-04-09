package edu.emergencytrainingpwa.security.jwt;

/*
  @author   kosta
  @project   emergency-training-pwa
  @class  JwtTool
  @version  1.0.0 
  @since 09.04.2025 - 23.57
*/

import static edu.emergencytrainingpwa.constant.AppConstant.ROLE;
import edu.emergencytrainingpwa.enums.Role;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Slf4j
@Component
public class JwtTool {
    private final Integer accessTokenValidTimeInMinutes;
    @Getter
    private final String accessTokenKey;

    public JwtTool(
        @Value("${accessTokenValidTimeInMinutes}") Integer accessTokenValidTimeInMinutes,
        @Value("${tokenKey}") String accessTokenKey) {
        this.accessTokenValidTimeInMinutes = accessTokenValidTimeInMinutes;
        this.accessTokenKey = accessTokenKey;
    }

    public String createAccessToken(String email, Role role) {
        ClaimsBuilder claims = Jwts.claims().subject(email);
        claims.add(ROLE, Collections.singleton(role.name()));
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MINUTE, accessTokenValidTimeInMinutes);
        return Jwts.builder()
            .claims(claims.build())
            .issuedAt(now)
            .expiration(calendar.getTime())
            .signWith(Keys.hmacShaKeyFor(
                    accessTokenKey.getBytes(StandardCharsets.UTF_8)),
                Jwts.SIG.HS256)
            .compact();
    }

    public String getTokenFromHttpServletRequest(HttpServletRequest servletRequest) {
        return Optional.ofNullable(servletRequest.getHeader("Authorization"))
            .filter(authHeader -> authHeader.startsWith("Bearer "))
            .map(token -> token.substring(7))
            .orElse(null);
    }
}
