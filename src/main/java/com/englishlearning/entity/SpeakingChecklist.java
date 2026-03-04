package com.englishlearning.entity;

public class SpeakingChecklist {

    private boolean endingSounds;
    private boolean linking;
    private boolean stress;
    private boolean clarity;

    public SpeakingChecklist() {
    }

    public SpeakingChecklist(boolean endingSounds, boolean linking, boolean stress, boolean clarity) {
        this.endingSounds = endingSounds;
        this.linking = linking;
        this.stress = stress;
        this.clarity = clarity;
    }

    public boolean isEndingSounds() {
        return endingSounds;
    }

    public void setEndingSounds(boolean endingSounds) {
        this.endingSounds = endingSounds;
    }

    public boolean isLinking() {
        return linking;
    }

    public void setLinking(boolean linking) {
        this.linking = linking;
    }

    public boolean isStress() {
        return stress;
    }

    public void setStress(boolean stress) {
        this.stress = stress;
    }

    public boolean isClarity() {
        return clarity;
    }

    public void setClarity(boolean clarity) {
        this.clarity = clarity;
    }
}
