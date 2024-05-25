package com.pp.surveyservice.service;

import com.pp.surveyservice.model.*;
import com.pp.surveyservice.repository.QuestionRepository;
import com.pp.surveyservice.repository.SurveyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SurveyServiceTest {

    @Mock
    SurveyRepository surveyRepository;
    @Mock
    QuestionRepository questionRepository;
    @InjectMocks
    private SurveyService surveyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_returns_correct_user_answers() {
        String surveyId = "survey1";
        String questionId = "question1";
        Survey survey = new Survey();
        Question question = new Question();
        question.setId(questionId);
        Option option = new Option();
        option.setText("Option1");
        option.setUsersSelected(List.of(new User()));
        question.setOptions(List.of(option));
        survey.setQuestions(List.of(question));

        when(surveyRepository.findById(surveyId)).thenReturn(Optional.of(survey));

        OptionsStatistics result = surveyService.getOptionStatistics(surveyId, questionId);

        assertNotNull(result);
        assertTrue(result.getUserAnswers().containsKey("Option1"));
        assertEquals(1, result.getUserAnswers().get("Option1").size());
    }
}