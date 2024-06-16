package com.pp.analysisservice.service;

import com.pp.analysisservice.model.Preference;
import com.pp.analysisservice.model.Suggestion;
import com.pp.analysisservice.repository.PreferenceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class SuggestionServiceTest {

    @Mock
    private PreferenceRepository preferenceRepository;

    @InjectMocks
    private SuggestionService suggestionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenUserPreferences_whenGenerateSuggestions_thenReturnExpectedSuggestions() {
        String userId = "user1";
        int suggestionsNumber = 2;

        List<Preference> userPreferences = Arrays.asList(
                new Preference(userId, "survey1", 3000L, true),
                new Preference(userId, "survey2", 1000L, false)
        );

        List<Preference> allPreferences = Arrays.asList(
                new Preference(userId, "survey1", 3000L, true),
                new Preference(userId, "survey2", 1000L, false),
                new Preference("user2", "survey3", 5000L, true),
                new Preference("user3", "survey4", 2000L, true),
                new Preference("user4", "survey5", 10000L, true)
        );

        when(preferenceRepository.findByUserId(userId)).thenReturn(userPreferences);
        when(preferenceRepository.findAll()).thenReturn(allPreferences);

        List<Suggestion> suggestions = suggestionService.generateSuggestions(userId, suggestionsNumber);

        assertEquals(2, suggestions.size());
        assertEquals("survey5", suggestions.get(0).getSuggestedSurveyId());
        assertEquals("survey3", suggestions.get(1).getSuggestedSurveyId());
    }

    @Test
    void givenNoUserPreferences_whenGenerateSuggestions_thenReturnMostPopularAndNewestSurveys() {
        String userId = "user1";
        int suggestionsNumber = 2;

        List<Preference> userPreferences = Collections.emptyList();

        List<Preference> allPreferences = Arrays.asList(
                new Preference("user2", "survey3", 5000L, true),
                new Preference("user3", "survey4", 2000L, true),
                new Preference("user4", "survey5", 10000L, true)
        );

        when(preferenceRepository.findByUserId(userId)).thenReturn(userPreferences);
        when(preferenceRepository.findAll()).thenReturn(allPreferences);

        List<Suggestion> suggestions = suggestionService.generateSuggestions(userId, suggestionsNumber);

        assertEquals(2, suggestions.size());
        assertEquals("survey5", suggestions.get(0).getSuggestedSurveyId());
        assertEquals("survey3", suggestions.get(1).getSuggestedSurveyId());
    }
}