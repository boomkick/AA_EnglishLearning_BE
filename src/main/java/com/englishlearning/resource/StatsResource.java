package com.englishlearning.resource;

import java.util.UUID;

import com.englishlearning.dto.UserStatsDto;
import com.englishlearning.service.StatsService;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/api/users/me/stats")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class StatsResource {

    @Inject
    StatsService statsService;

    @Inject
    JsonWebToken jwt;

    @GET
    public UserStatsDto getUserStats() {
        UUID userId = UUID.fromString(jwt.getSubject());
        return statsService.getUserStats(userId);
    }
}
