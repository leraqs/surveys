package com.pp.analysisservice.service;

import com.pp.analysisservice.model.Preference;
import com.pp.analysisservice.model.Suggestion;
import com.pp.analysisservice.repository.PreferenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SuggestionService {

    private final PreferenceRepository preferenceRepository;

    public List<Suggestion> generateSuggestions(String userId, int suggestionsNumber) {

        List<Suggestion> suggestions = new ArrayList<>();

        List<Preference> userPreferences = preferenceRepository.findByUserId(userId);
        List<Preference> allPreferences = preferenceRepository.findAll();

        Set<String> userSurveyIds = userPreferences.stream()
                .map(Preference::getSurveyId)
                .collect(Collectors.toSet());

        Map<String, Double> surveyScores = new HashMap<>();
        for (Preference preference : allPreferences) {
            if (!userSurveyIds.contains(preference.getSurveyId())) {
                surveyScores.merge(preference.getSurveyId(),
                        (preference.getLiked() ? 1.0 : 0.0) + (preference.getReadingTime() / 1000.0),
                        Double::sum);
            }
        }

        List<String> popularSurveyIds = surveyScores.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .limit(suggestionsNumber)
                .map(Map.Entry::getKey)
                .toList();

        for (String surveyId : popularSurveyIds) {
            Suggestion suggestion = new Suggestion();
            suggestion.setUserId(userId);
            suggestion.setSuggestedSurveyId(surveyId);
            suggestions.add(suggestion);
        }

        return suggestions;
    }
}

