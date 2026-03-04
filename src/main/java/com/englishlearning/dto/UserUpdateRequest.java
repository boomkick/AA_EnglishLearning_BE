package com.englishlearning.dto;

public class UserUpdateRequest {

    private String displayName;
    private Integer weeklyXpTarget;
    private Integer rewardPointsUsed;

    public UserUpdateRequest() {
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getWeeklyXpTarget() {
        return weeklyXpTarget;
    }

    public void setWeeklyXpTarget(Integer weeklyXpTarget) {
        this.weeklyXpTarget = weeklyXpTarget;
    }

    public Integer getRewardPointsUsed() {
        return rewardPointsUsed;
    }

    public void setRewardPointsUsed(Integer rewardPointsUsed) {
        this.rewardPointsUsed = rewardPointsUsed;
    }
}
