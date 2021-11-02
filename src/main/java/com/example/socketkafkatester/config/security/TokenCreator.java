package com.example.socketkafkatester.config.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TokenCreator {

    private static final String EMAIL = "email";
    private final JwtProperties jwtProperties;

    public TokenCreator(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String createToken(Authentication authentication) {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(EMAIL, authentication.getPrincipal())
                .signWith(Keys.hmacShaKeyFor(keyBytes), SignatureAlgorithm.HS512)
                .compact();
    }

}