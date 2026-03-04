package com.englishlearning.dto;

import java.util.Map;

public class UserStatsDto {

    private int streak;
    private int weeklyXpEarned;
    private Map<String, Integer> skillDistribution7d;

    public UserStatsDto() {
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public int getWeeklyXpEarned() {
        return weeklyXpEarned;
    }

    public void setWeeklyXpEarned(int weeklyXpEarned) {
        this.weeklyXpEarned = weeklyXpEarned;
    }

    public Map<String, Integer> getSkillDistribution7d() {
        return skillDistribution7d;
    }

    public void setSkillDistribution7d(Map<String, Integer> skillDistribution7d) {
        this.skillDistribution7d = skillDistribution7d;
    }
}
