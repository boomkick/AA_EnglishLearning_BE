package com.englishlearning.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.UUID;

import com.englishlearning.dto.ErrorResponse;
import com.englishlearning.dto.WeeklyPlanDto;
import com.englishlearning.dto.WeeklyPlanUpsertRequest;
import com.englishlearning.entity.AppUser;
import com.englishlearning.entity.WeeklyPlan;
import com.englishlearning.repository.AppUserRepository;
import com.englishlearning.repository.WeeklyPlanRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class WeeklyPlanService {

    @Inject
    WeeklyPlanRepository weeklyPlanRepository;

    @Inject
    AppUserRepository appUserRepository;

    public WeeklyPlanDto getCurrentPlan(UUID userId) {
        LocalDate monday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        WeeklyPlan plan = weeklyPlanRepository.findByUserIdAndWeekStartDate(userId, monday);
        if (plan == null) {
            throw new WebApplicationException(
                    Response.status(404).entity(ErrorResponse.of(404, "No plan for current week")).build());
        }
        return toDto(plan);
    }

    @Transactional
    public WeeklyPlanDto upsertCurrentPlan(UUID userId, WeeklyPlanUpsertRequest request) {
        LocalDate monday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        WeeklyPlan plan = weeklyPlanRepository.findByUserIdAndWeekStartDate(userId, monday);

        if (plan != null) {
            if (request.getXpTarget() != null) {
                plan.setXpTarget(request.getXpTarget());
            }
            if (request.getSkillFocusPercentages() != null) {
                plan.setSkillFocusPercentages(request.getSkillFocusPercentages());
            }
        } else {
            AppUser user = appUserRepository.findById(userId);
            plan = new WeeklyPlan();
            plan.setUser(user);
            plan.setWeekStartDate(monday);
            plan.setXpTarget(request.getXpTarget() != null ? request.getXpTarget() : 0);
            plan.setSkillFocusPercentages(request.getSkillFocusPercentages());
        }

        weeklyPlanRepository.persist(plan);
        return toDto(plan);
    }

    private WeeklyPlanDto toDto(WeeklyPlan plan) {
        WeeklyPlanDto dto = new WeeklyPlanDto();
        dto.setId(plan.getId().toString());
        dto.setUserId(plan.getUser().getId().toString());
        dto.setWeekStartDate(plan.getWeekStartDate().toString());
        dto.setXpTarget(plan.getXpTarget());
        dto.setSkillFocusPercentages(plan.getSkillFocusPercentages());
        return dto;
    }
}
