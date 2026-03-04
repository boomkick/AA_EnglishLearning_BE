package com.englishlearning.resource;

import com.englishlearning.dto.AuthResponse;
import com.englishlearning.dto.LoginRequest;
import com.englishlearning.dto.RegisterRequest;
import com.englishlearning.service.AuthService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    AuthService authService;

    @POST
    @Path("/register")
    public Response register(RegisterRequest request) {
        AuthResponse authResponse = authService.register(request.getEmail(), request.getPassword());
        return Response.status(201).entity(authResponse).build();
    }

    @POST
    @Path("/login")
    public Response login(LoginRequest request) {
        AuthResponse authResponse = authService.login(request.getEmail(), request.getPassword());
        return Response.ok(authResponse).build();
    }
}
