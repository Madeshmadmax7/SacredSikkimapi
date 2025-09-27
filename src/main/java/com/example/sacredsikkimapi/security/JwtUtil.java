package com.example.sacredsikkimapi.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // Replace this with your generated Base64 key
    private static final String SECRET = "D2aHq/qLMf5u7hOXzxUeepXa8NJmeHWplxkwGt3LfNQ=";

    // Convert secret to Key
    private final Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));

    private final long expiration = 1000 * 60 * 60 * 24; // 24 hours

    // Generate JWT
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract email from JWT
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
