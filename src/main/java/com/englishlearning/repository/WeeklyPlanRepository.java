package com.englishlearning.repository;

import java.time.LocalDate;
import java.util.UUID;

import com.englishlearning.entity.WeeklyPlan;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WeeklyPlanRepository implements PanacheRepositoryBase<WeeklyPlan, UUID> {

    public WeeklyPlan findByUserIdAndWeekStartDate(UUID userId, LocalDate weekStartDate) {
        return find("user.id = ?1 and weekStartDate = ?2", userId, weekStartDate).firstResult();
    }
}
