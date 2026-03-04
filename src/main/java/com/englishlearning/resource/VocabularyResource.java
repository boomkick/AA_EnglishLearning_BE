package com.englishlearning.resource;

import java.util.List;
import java.util.UUID;

import com.englishlearning.dto.VocabItemCreateRequest;
import com.englishlearning.dto.VocabItemDto;
import com.englishlearning.dto.VocabItemUpdateRequest;
import com.englishlearning.service.VocabularyService;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/api/users/me/vocabulary")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class VocabularyResource {

    @Inject
    VocabularyService vocabularyService;

    @Inject
    JsonWebToken jwt;

    @GET
    public List<VocabItemDto> listVocabulary(@QueryParam("topic") String topic) {
        UUID userId = UUID.fromString(jwt.getSubject());
        return vocabularyService.listVocabulary(userId, topic);
    }

    @POST
    public Response createVocabItem(VocabItemCreateRequest request) {
        UUID userId = UUID.fromString(jwt.getSubject());
        VocabItemDto dto = vocabularyService.createVocabItem(userId, request);
        return Response.status(201).entity(dto).build();
    }

    @PATCH
    @Path("/{id}")
    public VocabItemDto updateVocabItem(@PathParam("id") String id, VocabItemUpdateRequest request) {
        UUID userId = UUID.fromString(jwt.getSubject());
        return vocabularyService.updateVocabItem(userId, UUID.fromString(id), request);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteVocabItem(@PathParam("id") String id) {
        UUID userId = UUID.fromString(jwt.getSubject());
        vocabularyService.deleteVocabItem(userId, UUID.fromString(id));
        return Response.noContent().build();
    }
}
