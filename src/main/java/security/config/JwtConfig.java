package security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Value("${jwt.token.secret}")
    public String secret;

    @Value("${jwt.token.life-time}")
    public Integer lifeTime;
}
