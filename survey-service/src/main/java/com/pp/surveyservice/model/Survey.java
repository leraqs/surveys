package com.pp.surveyservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "surveys")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Survey implements Serializable {

    @Id
    private String id;

    private String title;

    private String description;

    private List<Question> questions;

    private String creatorUsername;

    @CreatedDate
    private LocalDateTime creationTime;

}
