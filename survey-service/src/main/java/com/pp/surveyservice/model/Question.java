package com.pp.surveyservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@Document(collection = "questions")
public class Question {

    @Id
    private String id;

    private String surveyId;

    private String text;

    private List<Option> options;

}
