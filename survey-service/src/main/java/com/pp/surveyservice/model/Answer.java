package com.pp.surveyservice.model;

import lombok.Data;

import java.util.List;

@Data
public class Answer {

    private String questionId;
    private List<Option> selectedOptions;
}
