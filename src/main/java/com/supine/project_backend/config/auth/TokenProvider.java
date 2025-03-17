package com.supine.project_backend.config.auth;

import java.time.Instant;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.supine.project_backend.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class TokenProvider {
    @Value("${security.jwt.secret-key}")
    private String JWT_SECRET;

    public String generateToken(User user) {
        try {
            Algorithm alg = Algorithm.HMAC256(JWT_SECRET);
            return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("user_id", user.getId())
                .withExpiresAt(getAccessExpirationDate())
                .sign(alg);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Error whil generating token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm alg = Algorithm.HMAC256(JWT_SECRET);
            return JWT.require(alg)
                .build()
                .verify(token)
                .getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Error while validating token", exception);
        }
    }

    public Instant getAccessExpirationDate() {
        return ZonedDateTime.now().plusHours(2).toInstant();
    }


    public Long getIdFromJwt(String jwtToken) {
        SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parserBuilder()
                           .setSigningKey(key)
                           .build()
                           .parseClaimsJws(jwtToken)
                           .getBody();
        return Long.valueOf(claims.get("user_id").toString());
    }

   
}
