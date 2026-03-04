package com.englishlearning.resource;

import java.util.List;
import java.util.UUID;

import com.englishlearning.dto.MissionLogCreateRequest;
import com.englishlearning.dto.MissionLogDto;
import com.englishlearning.service.MissionLogService;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/api/users/me/logs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class MissionLogResource {

    @Inject
    MissionLogService missionLogService;

    @Inject
    JsonWebToken jwt;

    @GET
    public List<MissionLogDto> listLogs(@QueryParam("fromDate") String fromDate,
                                        @QueryParam("toDate") String toDate) {
        UUID userId = UUID.fromString(jwt.getSubject());
        return missionLogService.listLogs(userId, fromDate, toDate);
    }

    @POST
    public Response createLog(MissionLogCreateRequest request) {
        UUID userId = UUID.fromString(jwt.getSubject());
        MissionLogDto dto = missionLogService.createLog(userId, request);
        return Response.status(201).entity(dto).build();
    }
}
