package com.englishlearning.dto;

public class SpeakingChecklistDto {

    private Boolean endingSounds;
    private Boolean linking;
    private Boolean stress;
    private Boolean clarity;

    public SpeakingChecklistDto() {
    }

    public Boolean getEndingSounds() {
        return endingSounds;
    }

    public void setEndingSounds(Boolean endingSounds) {
        this.endingSounds = endingSounds;
    }

    public Boolean getLinking() {
        return linking;
    }

    public void setLinking(Boolean linking) {
        this.linking = linking;
    }

    public Boolean getStress() {
        return stress;
    }

    public void setStress(Boolean stress) {
        this.stress = stress;
    }

    public Boolean getClarity() {
        return clarity;
    }

    public void setClarity(Boolean clarity) {
        this.clarity = clarity;
    }
}
