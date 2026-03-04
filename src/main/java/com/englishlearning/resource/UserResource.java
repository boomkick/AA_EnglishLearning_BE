package com.englishlearning.resource;

import java.util.UUID;

import com.englishlearning.dto.UserDto;
import com.englishlearning.dto.UserUpdateRequest;
import com.englishlearning.entity.AppUser;
import com.englishlearning.repository.AppUserRepository;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/api/users/me")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class UserResource {

    @Inject
    AppUserRepository appUserRepository;

    @Inject
    JsonWebToken jwt;

    @GET
    public Response getProfile() {
        UUID userId = UUID.fromString(jwt.getSubject());
        AppUser user = appUserRepository.findById(userId);
        if (user == null) {
            throw new WebApplicationException("User not found", Response.Status.NOT_FOUND);
        }
        return Response.ok(toUserDto(user)).build();
    }

    @PATCH
    @Transactional
    public Response updateProfile(UserUpdateRequest request) {
        UUID userId = UUID.fromString(jwt.getSubject());
        AppUser user = appUserRepository.findById(userId);
        if (user == null) {
            throw new WebApplicationException("User not found", Response.Status.NOT_FOUND);
        }

        if (request.getDisplayName() != null) {
            user.setDisplayName(request.getDisplayName());
        }
        if (request.getWeeklyXpTarget() != null) {
            user.setWeeklyXpTarget(request.getWeeklyXpTarget());
        }
        if (request.getRewardPointsUsed() != null) {
            user.setRewardPointsUsed(request.getRewardPointsUsed());
        }

        appUserRepository.persist(user);
        return Response.ok(toUserDto(user)).build();
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
