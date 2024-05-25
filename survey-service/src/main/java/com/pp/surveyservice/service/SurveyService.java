package com.pp.surveyservice.service;

import com.pp.surveyservice.exceptions.QuestionNotFoundException;
import com.pp.surveyservice.exceptions.SurveyNotFoundException;
import com.pp.surveyservice.model.Question;
import com.pp.surveyservice.model.Survey;
import com.pp.surveyservice.model.UserResponse;
import com.pp.surveyservice.repository.QuestionRepository;
import com.pp.surveyservice.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;

    public Survey createSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    public List<UserResponse> getOptionStatistics(String surveyId, String questionId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new SurveyNotFoundException(surveyId));

        Question question = survey.getQuestions().stream()
                .filter(q -> q.getId().equals(questionId))
                .findFirst()
                .orElseThrow(() -> new QuestionNotFoundException(questionId));

        return question.getOptions().stream()
                .flatMap(option -> option.getUserResponses().stream())
                .toList();
    }
}

