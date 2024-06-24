package com.pp.surveyservice.model;

import java.util.List;

public record CreateSurveyRequest(String title,
                                  String description,
                                  List<Question> questions,
                                  String creatorUsername) {
}
