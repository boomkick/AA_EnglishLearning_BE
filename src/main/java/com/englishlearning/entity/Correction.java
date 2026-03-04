package com.englishlearning.entity;

public class Correction {

    private String from;
    private String to;
    private String note;

    public Correction() {
    }

    public Correction(String from, String to, String note) {
        this.from = from;
        this.to = to;
        this.note = note;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
