package com.pp.surveyservice.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;


@Data
public class Option {

    private String questionId;

    private String text;

    @DBRef(db = "user_responses")
    private List<UserResponse> userResponses;
}
