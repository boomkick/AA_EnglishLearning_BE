package com.englishlearning.repository;

import java.util.List;
import java.util.UUID;

import com.englishlearning.entity.SpeakingFeedback;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SpeakingFeedbackRepository implements PanacheRepositoryBase<SpeakingFeedback, UUID> {

    public List<SpeakingFeedback> findByUserId(UUID userId) {
        return find("user.id", Sort.by("createdAt").descending(), userId).list();
    }
}
