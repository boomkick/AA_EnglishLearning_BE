package com.englishlearning.dto;

import java.util.List;

public class FeedbackListDto {

    private List<WritingFeedbackDto> writing;
    private List<SpeakingFeedbackDto> speaking;

    public FeedbackListDto() {
    }

    public List<WritingFeedbackDto> getWriting() {
        return writing;
    }

    public void setWriting(List<WritingFeedbackDto> writing) {
        this.writing = writing;
    }

    public List<SpeakingFeedbackDto> getSpeaking() {
        return speaking;
    }

    public void setSpeaking(List<SpeakingFeedbackDto> speaking) {
        this.speaking = speaking;
    }
}
