package com.englishlearning.security;

import io.smallrye.jwt.algorithm.SignatureAlgorithm;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class JwtService {
    @ConfigProperty(name = "app.jwt.expiration-seconds")
    long expirationSeconds;

    public String generateToken(UUID userId, String email) {

        return Jwt.issuer("english-learning-api")
                .subject(userId.toString())
                .upn(email)
                .groups(Set.of("user"))
                .expiresAt(Instant.now().plusSeconds(expirationSeconds))
                .sign();
    }
}