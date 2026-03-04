package com.englishlearning.dto;

import java.util.Map;

public class WeeklyPlanUpsertRequest {

    private Integer xpTarget;
    private Map<String, Integer> skillFocusPercentages;

    public WeeklyPlanUpsertRequest() {
    }

    public Integer getXpTarget() {
        return xpTarget;
    }

    public void setXpTarget(Integer xpTarget) {
        this.xpTarget = xpTarget;
    }

    public Map<String, Integer> getSkillFocusPercentages() {
        return skillFocusPercentages;
    }

    public void setSkillFocusPercentages(Map<String, Integer> skillFocusPercentages) {
        this.skillFocusPercentages = skillFocusPercentages;
    }
}
