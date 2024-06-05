package com.pp.surveyservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Document(collection = "user_responses")
public class UserResponse {

    @Id
    private String id;

    private String userId;

    private String surveyId;

    /**
     * questionId, selected options
     */
    private Map<String, List<Option>> answers;

    private Map<String, List<Option>> groupingAnswers;
}
