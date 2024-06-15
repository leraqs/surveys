package com.pp.analysisservice.service;

import com.pp.analysisservice.model.Preference;
import com.pp.analysisservice.repository.PreferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PreferenceService {

    private final PreferenceRepository preferenceRepository;

    public List<Preference> getPreferencesByUserId(String userId) {
        return preferenceRepository.findByUserId(userId);
    }

    public List<Preference> getPreferencesBySurveyId(String surveyId) {
        return preferenceRepository.findBySurveyId(surveyId);
    }
}
