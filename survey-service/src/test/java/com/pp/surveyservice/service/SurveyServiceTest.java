package com.pp.surveyservice.service;

import com.pp.surveyservice.model.*;
import com.pp.surveyservice.repository.QuestionRepository;
import com.pp.surveyservice.repository.SurveyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public void returns_user_responses_when_survey_and_question_found() {
        String surveyId = "s1";
        String questionId = "q1";
        Survey survey = new Survey();
        Question question = new Question();
        question.setId(questionId);
        Option option = new Option();
        option.setText("Option1");
        List<Option> options = List.of(option);
        question.setOptions(options);
        Map<String, List<Option>> answers = new HashMap<>();
        answers.put(questionId, options);
        UserResponse userResponse = new UserResponse();
        userResponse.setAnswers(answers);
        option.setUserResponses(List.of(userResponse));
        survey.setQuestions(List.of(question));
        when(surveyRepository.findById(surveyId)).thenReturn(Optional.of(survey));

        List<UserResponse> result = surveyService.getOptionStatistics(surveyId, questionId);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.stream().anyMatch(r -> r.getAnswers().containsKey(questionId)));
    }
}