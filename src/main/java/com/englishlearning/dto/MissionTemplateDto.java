package com.englishlearning.dto;

public class MissionTemplateDto {

    private String id;
    private String skillId;
    private String title;
    private String description;
    private int xp;
    private int estMinutes;
    private int difficulty;

    public MissionTemplateDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getEstMinutes() {
        return estMinutes;
    }

    public void setEstMinutes(int estMinutes) {
        this.estMinutes = estMinutes;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
