package com.pp.analysisservice.service;

import com.pp.analysisservice.model.Preference;
import com.pp.analysisservice.model.Suggestion;
import com.pp.analysisservice.repository.PreferenceRepository;
import com.pp.analysisservice.utils.SuggestionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SuggestionService {

    private final PreferenceRepository preferenceRepository;

    public List<Suggestion> generateSuggestions(String userId, int suggestionsNumber) {
        List<Preference> userPreferences = preferenceRepository.findByUserId(userId);
        List<Preference> allPreferences = preferenceRepository.findAll();

        Set<String> userSurveyIds = SuggestionUtils.getUserSurveyIds(userPreferences);
        Map<String, Double> surveyScores = SuggestionUtils.calculateSurveyScores(allPreferences, userSurveyIds);

        List<String> popularSurveyIds = userPreferences.isEmpty()
                ? SuggestionUtils.getMostPopularSurveyIds(allPreferences, suggestionsNumber)
                : SuggestionUtils.getTopSurveyIds(surveyScores, suggestionsNumber);

        return createSuggestions(userId, popularSurveyIds);
    }

    private List<Suggestion> createSuggestions(String userId, List<String> surveyIds) {
        return surveyIds.stream()
                .map(surveyId -> Suggestion.builder()
                        .userId(userId)
                        .suggestedSurveyId(surveyId)
                        .build())
                .toList();
    }
}
