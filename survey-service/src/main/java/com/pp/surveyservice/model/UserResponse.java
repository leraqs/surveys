package com.pp.surveyservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "user_responses")
public class UserResponse {

    @Id
    private String id;

    private String userId;

    private String surveyId;

    private List<Answer> answers;

    private List<Answer> groupingAnswers;
}

