package com.englishlearning.entity;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "weekly_plan")
public class WeeklyPlan {

    @Id
    private UUID id;

    private LocalDate weekStartDate;

    private int xpTarget;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Integer> skillFocusPercentages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUser user;

    public WeeklyPlan() {
    }

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(LocalDate weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public int getXpTarget() {
        return xpTarget;
    }

    public void setXpTarget(int xpTarget) {
        this.xpTarget = xpTarget;
    }

    public Map<String, Integer> getSkillFocusPercentages() {
        return skillFocusPercentages;
    }

    public void setSkillFocusPercentages(Map<String, Integer> skillFocusPercentages) {
        this.skillFocusPercentages = skillFocusPercentages;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
