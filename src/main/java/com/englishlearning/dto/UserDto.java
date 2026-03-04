package com.englishlearning.dto;

public class UserDto {

    private String id;
    private String email;
    private String displayName;
    private int xpTotal;
    private int weeklyXpTarget;
    private int rewardPointsUsed;
    private String createdAt;
    private String updatedAt;

    public UserDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getXpTotal() {
        return xpTotal;
    }

    public void setXpTotal(int xpTotal) {
        this.xpTotal = xpTotal;
    }

    public int getWeeklyXpTarget() {
        return weeklyXpTarget;
    }

    public void setWeeklyXpTarget(int weeklyXpTarget) {
        this.weeklyXpTarget = weeklyXpTarget;
    }

    public int getRewardPointsUsed() {
        return rewardPointsUsed;
    }

    public void setRewardPointsUsed(int rewardPointsUsed) {
        this.rewardPointsUsed = rewardPointsUsed;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
