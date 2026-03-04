package com.englishlearning.dto;

import java.util.List;

public class WritingFeedbackCreateRequest {

    private String title;
    private String prompt;
    private String text;
    private List<CorrectionDto> corrections;
    private List<String> repeatedMistakeTags;

    public WritingFeedbackCreateRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<CorrectionDto> getCorrections() {
        return corrections;
    }

    public void setCorrections(List<CorrectionDto> corrections) {
        this.corrections = corrections;
    }

    public List<String> getRepeatedMistakeTags() {
        return repeatedMistakeTags;
    }

    public void setRepeatedMistakeTags(List<String> repeatedMistakeTags) {
        this.repeatedMistakeTags = repeatedMistakeTags;
    }
}
