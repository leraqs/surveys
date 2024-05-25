package com.pp.surveyservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "surveys")
public class Survey {

    @Id
    private String id;

    private String title;

    private String description;

    @DBRef(db = "questions")
    private List<Question> questions;

    private String creatorId;

    private LocalDateTime creationTime;

}
