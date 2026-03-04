package com.englishlearning.resource;

import java.util.UUID;

import com.englishlearning.dto.WeeklyPlanDto;
import com.englishlearning.dto.WeeklyPlanUpsertRequest;
import com.englishlearning.service.WeeklyPlanService;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/api/users/me/plans/current")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class WeeklyPlanResource {

    @Inject
    WeeklyPlanService weeklyPlanService;

    @Inject
    JsonWebToken jwt;

    @GET
    public WeeklyPlanDto getCurrentPlan() {
        UUID userId = UUID.fromString(jwt.getSubject());
        return weeklyPlanService.getCurrentPlan(userId);
    }

    @PUT
    public WeeklyPlanDto upsertCurrentPlan(WeeklyPlanUpsertRequest request) {
        UUID userId = UUID.fromString(jwt.getSubject());
        return weeklyPlanService.upsertCurrentPlan(userId, request);
    }
}
