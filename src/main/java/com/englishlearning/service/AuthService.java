package com.englishlearning.service;

import com.englishlearning.dto.AuthResponse;
import com.englishlearning.dto.UserDto;
import com.englishlearning.entity.AppUser;
import com.englishlearning.repository.AppUserRepository;
import com.englishlearning.security.JwtService;
import io.quarkus.elytron.security.common.BcryptUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import org.wildfly.security.password.PasswordFactory;
import org.wildfly.security.password.interfaces.BCryptPassword;
import org.wildfly.security.password.util.ModularCrypt;

@ApplicationScoped
public class AuthService {

    @Inject
    AppUserRepository appUserRepository;

    @Inject
    JwtService jwtService;

    @Transactional
    public AuthResponse register(String email, String password) {
        AppUser existing = appUserRepository.findByEmail(email);
        if (existing != null) {
            throw new WebApplicationException("Email already in use", Response.Status.CONFLICT);
        }

        AppUser user = new AppUser();
        user.setEmail(email);
        user.setPasswordHash(BcryptUtil.bcryptHash(password));
        appUserRepository.persist(user);

        String token = jwtService.generateToken(user.getId(), user.getEmail());

        AuthResponse response = new AuthResponse();
        response.setUserId(user.getId().toString());
        response.setToken(token);
        response.setUser(toUserDto(user));
        return response;
    }

    public AuthResponse login(String email, String password) {
        AppUser user = appUserRepository.findByEmail(email);
        if (user == null) {
            throw new WebApplicationException("Invalid credentials", Response.Status.UNAUTHORIZED);
        }

        if (!verifyPassword(password, user.getPasswordHash())) {
            throw new WebApplicationException("Invalid credentials", Response.Status.UNAUTHORIZED);
        }

        String token = jwtService.generateToken(user.getId(), user.getEmail());

        AuthResponse response = new AuthResponse();
        response.setUserId(user.getId().toString());
        response.setToken(token);
        response.setUser(toUserDto(user));
        return response;
    }

    private boolean verifyPassword(String plainText, String bcryptHash) {
        try {
            PasswordFactory pf = PasswordFactory.getInstance(BCryptPassword.ALGORITHM_BCRYPT);
            BCryptPassword restored = (BCryptPassword) pf.translate(ModularCrypt.decode(bcryptHash));
            return pf.verify(restored, plainText.toCharArray());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException e) {
            return false;
        }
    }

    private UserDto toUserDto(AppUser user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId().toString());
        dto.setEmail(user.getEmail());
        dto.setDisplayName(user.getDisplayName());
        dto.setXpTotal(user.getXpTotal());
        dto.setWeeklyXpTarget(user.getWeeklyXpTarget());
        dto.setRewardPointsUsed(user.getRewardPointsUsed());
        dto.setCreatedAt(user.getCreatedAt() != null ? user.getCreatedAt().toString() : null);
        dto.setUpdatedAt(user.getUpdatedAt() != null ? user.getUpdatedAt().toString() : null);
        return dto;
    }
}
