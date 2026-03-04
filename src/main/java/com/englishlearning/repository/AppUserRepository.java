package com.englishlearning.repository;

import java.util.UUID;

import com.englishlearning.entity.AppUser;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AppUserRepository implements PanacheRepositoryBase<AppUser, UUID> {

    public AppUser findByEmail(String email) {
        return find("email", email).firstResult();
    }
}
