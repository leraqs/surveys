package com.pp.surveyservice.exceptions;

public class SurveyNotFoundException extends RuntimeException {
    public SurveyNotFoundException(String id) {
        super("Survey not found with ID: " + id);
    }
}
