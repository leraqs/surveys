package com.pp.notificationservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record SurveyMessage(@JsonProperty("creator-username") String creatorUsername,
                            @JsonProperty("title") String title)
        implements Serializable {
}
