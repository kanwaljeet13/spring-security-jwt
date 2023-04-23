package com.codefusion.jwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtService {

    @Value("${app.jwt.issuer}")
    private String jwtIssuer;

    @Value("${app.jwt.secret.key}")
    private String jwtSecretKey;

    @Value("${app.jwt.expiration.ms}")
    private long jwtExpirationMs;

    public String generateToken(String userName) {
        return Jwts.builder()
                .setSubject(userName)
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+jwtExpirationMs))
                .signWith(getSignKey(), SignatureAlgorithm.HS512).compact();
    }

    public boolean validateToken(String username, String token) {
        try {
            Claims claims = parseJwtToken(token);
            return claims.getSubject() != null && username != null && claims.getSubject().equals(username);
        } catch (MalformedJwtException | ExpiredJwtException | IllegalArgumentException |
                 UnsupportedJwtException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public String refreshToken(String token) {
        Claims claims = parseJwtToken(token);
        return generateToken(claims.getSubject());
    }

    public Claims parseJwtToken(String jwtToken) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    public String extractUsername(String jwtToken) {
        return parseJwtToken(jwtToken).getSubject();
    }

    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
