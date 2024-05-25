package com.pp.surveyservice.model;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class OptionsStatistics {
    private Map<String, List<User>> userAnswers = new HashMap<>();
}
