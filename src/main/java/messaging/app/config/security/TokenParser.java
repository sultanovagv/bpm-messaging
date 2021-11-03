package messaging.app.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.InvalidParameterException;
import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TokenParser {

    private Key key;
    private final JwtProperties jwtProperties;

    @PostConstruct
    public void init() {
        byte[] keyBytes;
        keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public Authentication getAuthenticationBearer(String header) {
        if (!header.startsWith("Bearer")) {
            throw new InvalidParameterException("Invalid header");
        }
        String token = header.substring("Bearer".length()).trim();
        Claims claims = parseToken(token);
        String principal = claims.get("email", String.class);
        return new UsernamePasswordAuthenticationToken(principal, token, null);
    }

    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(this.key)
                .parseClaimsJws(token)
                .getBody();
    }


}
