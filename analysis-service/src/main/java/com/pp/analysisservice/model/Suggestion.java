package com.pp.analysisservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Suggestion {
    private String userId;
    private String suggestedSurveyId;
}
