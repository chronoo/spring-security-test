package security.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import security.config.JwtConfig;

import java.util.Date;

@Component
@AllArgsConstructor
public class JwtTokenProvider {
    private final JwtConfig config;

    public String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + (config.lifeTime * 60 * 1000)))
                .signWith(SignatureAlgorithm.HS256, config.secret)
                .compact();
    }
}
