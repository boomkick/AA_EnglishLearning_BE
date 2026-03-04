package com.englishlearning.dto;

public class MissionLogCreateRequest {

    private String missionTemplateId;
    private String date;
    private Integer durationMinutes;
    private Integer difficulty;
    private String notes;

    public MissionLogCreateRequest() {
    }

    public String getMissionTemplateId() {
        return missionTemplateId;
    }

    public void setMissionTemplateId(String missionTemplateId) {
        this.missionTemplateId = missionTemplateId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
