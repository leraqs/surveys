package com.pp.surveyservice.exceptions;

public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException(String questionId) {
        super("Question not found with ID: " + questionId);
    }
}
