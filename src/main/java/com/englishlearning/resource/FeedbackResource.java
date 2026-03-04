package com.englishlearning.resource;

import java.util.UUID;

import com.englishlearning.dto.FeedbackListDto;
import com.englishlearning.dto.SpeakingFeedbackCreateRequest;
import com.englishlearning.dto.SpeakingFeedbackDto;
import com.englishlearning.dto.SpeakingFeedbackUpdateRequest;
import com.englishlearning.dto.WritingFeedbackCreateRequest;
import com.englishlearning.dto.WritingFeedbackDto;
import com.englishlearning.dto.WritingFeedbackUpdateRequest;
import com.englishlearning.service.FeedbackService;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/api/users/me/feedback")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class FeedbackResource {

    @Inject
    FeedbackService feedbackService;

    @Inject
    JsonWebToken jwt;

    @GET
    public FeedbackListDto listFeedback() {
        UUID userId = UUID.fromString(jwt.getSubject());
        return feedbackService.listFeedback(userId);
    }

    @POST
    @Path("/writing")
    public Response createWritingFeedback(WritingFeedbackCreateRequest request) {
        UUID userId = UUID.fromString(jwt.getSubject());
        WritingFeedbackDto dto = feedbackService.createWritingFeedback(userId, request);
        return Response.status(201).entity(dto).build();
    }

    @PATCH
    @Path("/writing/{id}")
    public WritingFeedbackDto updateWritingFeedback(@PathParam("id") String id,
                                                    WritingFeedbackUpdateRequest request) {
        UUID userId = UUID.fromString(jwt.getSubject());
        return feedbackService.updateWritingFeedback(userId, UUID.fromString(id), request);
    }

    @POST
    @Path("/speaking")
    public Response createSpeakingFeedback(SpeakingFeedbackCreateRequest request) {
        UUID userId = UUID.fromString(jwt.getSubject());
        SpeakingFeedbackDto dto = feedbackService.createSpeakingFeedback(userId, request);
        return Response.status(201).entity(dto).build();
    }

    @PATCH
    @Path("/speaking/{id}")
    public SpeakingFeedbackDto updateSpeakingFeedback(@PathParam("id") String id,
                                                      SpeakingFeedbackUpdateRequest request) {
        UUID userId = UUID.fromString(jwt.getSubject());
        return feedbackService.updateSpeakingFeedback(userId, UUID.fromString(id), request);
    }
}
