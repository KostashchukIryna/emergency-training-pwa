package edu.emergencytrainingpwa.security.providers;

import edu.emergencytrainingpwa.enums.Role;
import edu.emergencytrainingpwa.security.jwt.JwtTool;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import java.util.List;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static edu.emergencytrainingpwa.constant.AppConstant.ROLE;

@RequiredArgsConstructor
public class JWTAuthenticationProvider implements AuthenticationProvider {
    private final JwtTool jwtTool;

    @Override
    public Authentication authenticate(Authentication authentication) {
        SecretKey key = Keys.hmacShaKeyFor(jwtTool.getAccessTokenKey().getBytes());
        String email = Jwts.parser()
            .verifyWith(key).build()
            .parseSignedClaims(authentication.getName())
            .getPayload()
            .getSubject();
        @SuppressWarnings({"unchecked, rawtype"})
        List<String> authorities = (List<String>) Jwts.parser()
            .verifyWith(key).build()
            .parseSignedClaims(authentication.getName())
            .getPayload()
            .get(ROLE);
        return new UsernamePasswordAuthenticationToken(
            email,
            "",
            authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
