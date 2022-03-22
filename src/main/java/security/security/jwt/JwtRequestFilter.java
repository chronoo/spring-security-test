package security.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import security.config.JwtConfig;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@AllArgsConstructor
@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    private final UserDetailsService service;
    private final JwtConfig config;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        checkToken(request);
        filterChain.doFilter(request, response);
    }

    private void checkToken(HttpServletRequest request) {
        try {
            String jwt = request.getHeader("Authorization").substring(7);
            Claims body = Jwts.parser()
                    .setSigningKey(config.secret)
                    .parseClaimsJws(jwt)
                    .getBody();
            String username = body.getSubject();
            UserDetails userDetails = service.loadUserByUsername(username);
            if (userDetails != null && !body.getExpiration().before(new Date())) {
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            log.error("Invalid token", e);
        }
    }
}
