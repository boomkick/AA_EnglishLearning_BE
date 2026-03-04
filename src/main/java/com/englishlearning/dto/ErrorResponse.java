package com.englishlearning.dto;

import java.util.List;

public class ErrorResponse {

    private int status;
    private String message;
    private List<String> details;

    public ErrorResponse() {
    }

    public static ErrorResponse of(int status, String message) {
        ErrorResponse response = new ErrorResponse();
        response.status = status;
        response.message = message;
        response.details = null;
        return response;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
