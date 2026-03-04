package com.englishlearning.repository;

import java.util.List;
import java.util.UUID;

import com.englishlearning.entity.VocabItem;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VocabItemRepository implements PanacheRepositoryBase<VocabItem, UUID> {

    public List<VocabItem> findByUserId(UUID userId) {
        return find("user.id", userId).list();
    }

    public VocabItem findByIdAndUserId(UUID id, UUID userId) {
        return find("id = ?1 and user.id = ?2", id, userId).firstResult();
    }
}
