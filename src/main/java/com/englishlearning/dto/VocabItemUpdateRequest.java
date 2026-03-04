package com.englishlearning.dto;

import java.util.List;

public class VocabItemUpdateRequest {

    private String term;
    private List<String> topicTags;
    private List<String> collocations;
    private List<String> examples;
    private String usageNotes;

    public VocabItemUpdateRequest() {
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public List<String> getTopicTags() {
        return topicTags;
    }

    public void setTopicTags(List<String> topicTags) {
        this.topicTags = topicTags;
    }

    public List<String> getCollocations() {
        return collocations;
    }

    public void setCollocations(List<String> collocations) {
        this.collocations = collocations;
    }

    public List<String> getExamples() {
        return examples;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

    public String getUsageNotes() {
        return usageNotes;
    }

    public void setUsageNotes(String usageNotes) {
        this.usageNotes = usageNotes;
    }
}
