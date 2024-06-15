package com.pp.analysisservice.controller;

import com.pp.analysisservice.model.Preference;
import com.pp.analysisservice.service.PreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/preferences")
public class PreferenceController {

    private final PreferenceService preferenceService;

    @GetMapping("/user/{userId}")
    public List<Preference> getPreferencesByUserId(@PathVariable String userId) {
        return preferenceService.getPreferencesByUserId(userId);
    }

    @GetMapping("/user/{surveyId}")
    public List<Preference> getPreferencesBySurveyId(@PathVariable String surveyId) {
        return preferenceService.getPreferencesBySurveyId(surveyId);
    }
}
