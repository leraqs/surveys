package com.pp.surveyservice.service;

import com.pp.surveyservice.exceptions.QuestionNotFoundException;
import com.pp.surveyservice.exceptions.SurveyNotFoundException;
import com.pp.surveyservice.model.*;
import com.pp.surveyservice.repository.QuestionRepository;
import com.pp.surveyservice.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;

    public Survey createSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    public OptionsStatistics getOptionStatistics(String surveyId, String questionId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new SurveyNotFoundException(surveyId));

        Question question = survey.getQuestions().stream()
                .filter(q -> q.getId().equals(questionId))
                .findFirst()
                .orElseThrow(() -> new QuestionNotFoundException(questionId));

        Map<String, List<User>> optionCounts = question.getOptions().stream()
                .collect(Collectors.toMap(Option::getText, Option::getUsersSelected));

        OptionsStatistics optionsStatistics = new OptionsStatistics();
        optionsStatistics.setUserAnswers(optionCounts);
        return optionsStatistics;
    }
}

