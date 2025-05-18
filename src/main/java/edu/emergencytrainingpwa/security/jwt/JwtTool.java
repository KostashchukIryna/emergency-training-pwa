package edu.emergencytrainingpwa.security.jwt;

/*
  @author   kosta
  @project   emergency-training-pwa
  @class  JwtTool
  @version  1.0.0 
  @since 09.04.2025 - 23.57
*/

import static edu.emergencytrainingpwa.constant.AppConstant.ROLE;

import edu.emergencytrainingpwa.dto.user.UserSecurityDto;
import edu.emergencytrainingpwa.enums.Role;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.*;
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
    private final Integer refreshTokenValidTimeInMinutes;

    public JwtTool(
        @Value("${accessTokenValidTimeInMinutes}") Integer accessTokenValidTimeInMinutes,
        @Value("${tokenKey}") String accessTokenKey,
        @Value("${refreshTokenValidTimeInMinutes}") Integer refreshTokenValidTimeInMinutes) {
        this.accessTokenValidTimeInMinutes = accessTokenValidTimeInMinutes;
        this.refreshTokenValidTimeInMinutes = refreshTokenValidTimeInMinutes;
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

    public String generateTokenKey() {
        return UUID.randomUUID().toString();
    }

    public String createRefreshToken(UserSecurityDto user, String email) {
        ClaimsBuilder claims = Jwts.claims().subject(email);
        claims.add(ROLE, Collections.singleton(user.getRole().name()));
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MINUTE, refreshTokenValidTimeInMinutes);
        return Jwts.builder()
            .claims(claims.build())
            .issuedAt(now)
            .expiration(calendar.getTime())
            .signWith(
                Keys.hmacShaKeyFor(user.getRefreshTokenKey().getBytes(StandardCharsets.UTF_8)),
                Jwts.SIG.HS256)
            .compact();
    }

    public String generateTokenKeyWithCodedDate() {
        Date date = new Date();
        long dateLong = date.getTime();
        dateLong += 86400000L;
        String input = dateLong + "." + UUID.randomUUID();
        return Base64.getEncoder().encodeToString(input.getBytes());
    }
}
