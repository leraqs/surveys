package com.pp.analysisservice.service;

import com.pp.analysisservice.model.Preference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SuggestionUtils {

    public static Set<String> getUserSurveyIds(List<Preference> userPreferences) {
        return userPreferences.stream()
                .map(Preference::getSurveyId)
                .collect(Collectors.toSet());
    }

    public static Map<String, Double> calculateSurveyScores(
            List<Preference> allPreferences,
            Set<String> userSurveyIds) {
        Map<String, Double> surveyScores = new HashMap<>();
        for (Preference preference : allPreferences) {
            if (!userSurveyIds.contains(preference.getSurveyId())) {
                surveyScores.merge(preference.getSurveyId(),
                        (preference.getLiked() ? 1.0 : 0.0) + (preference.getReadingTime() / 1000.0),
                        Double::sum);
            }
        }
        return surveyScores;
    }

    public static List<String> getTopSurveyIds(Map<String, Double> surveyScores, int limit) {
        return surveyScores.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .limit(limit)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static List<String> getMostPopularSurveyIds(List<Preference> allPreferences, int limit) {
        return allPreferences.stream()
                .collect(Collectors.groupingBy(Preference::getSurveyId, Collectors.summingDouble(p ->
                        (p.getLiked() ? 1.0 : 0.0) + (p.getReadingTime() / 1000.0))))
                .entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .limit(limit)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
