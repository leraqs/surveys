package com.pp.surveyservice.controller;

import com.pp.surveyservice.model.Survey;
import com.pp.surveyservice.model.UserResponse;
import com.pp.surveyservice.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/survey")
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Survey createSurvey(@Validated @RequestBody Survey survey) {
        return surveyService.createSurvey(survey);
    }

    @GetMapping("/statistics")
    public List<UserResponse> getOptionStatistics(@RequestBody String surveyId, String questionId) {
        return surveyService.getOptionStatistics(surveyId, questionId);
    }
}

