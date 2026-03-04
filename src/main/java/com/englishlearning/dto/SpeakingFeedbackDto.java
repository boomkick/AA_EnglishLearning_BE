package com.englishlearning.dto;

import java.util.List;

public class SpeakingFeedbackDto {

    private String id;
    private String userId;
    private String createdAt;
    private String title;
    private String audioFileName;
    private SpeakingChecklistDto checklist;
    private String comments;
    private List<String> issueTags;

    public SpeakingFeedbackDto() {
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAudioFileName() {
        return audioFileName;
    }

    public void setAudioFileName(String audioFileName) {
        this.audioFileName = audioFileName;
    }

    public SpeakingChecklistDto getChecklist() {
        return checklist;
    }

    public void setChecklist(SpeakingChecklistDto checklist) {
        this.checklist = checklist;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<String> getIssueTags() {
        return issueTags;
    }

    public void setIssueTags(List<String> issueTags) {
        this.issueTags = issueTags;
    }
}
