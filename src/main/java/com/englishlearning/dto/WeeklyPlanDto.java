package com.englishlearning.dto;

import java.util.Map;

public class WeeklyPlanDto {

    private String id;
    private String userId;
    private String weekStartDate;
    private int xpTarget;
    private Map<String, Integer> skillFocusPercentages;

    public WeeklyPlanDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(String weekStartDate) {
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
}
