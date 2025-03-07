package com.supine.project_backend.config.auth;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.supine.project_backend.model.User;

@Service
public class TokenProvider {
    @Value("${security.jwt.secret-key}")
    private String JWT_SECRET;

    public String generateToken(User user) {
        try {
            Algorithm alg = Algorithm.HMAC256(JWT_SECRET);
            return JWT.create()
                .withSubject(user.getFirstName())
                .withClaim("user_email", user.getEmail())
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
}
