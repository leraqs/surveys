package com.pp.analysisservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "preferences")
public class Preference {

    @Id
    private String id;

    private String userId;

    private String surveyId;

    private Long readingTime;  // In seconds

    private Boolean liked;

    public Preference(String userId, String surveyId, Long readingTime, Boolean liked) {
        this.userId = userId;
        this.surveyId = surveyId;
        this.readingTime = readingTime;
        this.liked = liked;
    }
}

