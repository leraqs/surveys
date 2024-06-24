package com.pp.surveyservice.service;

import com.pp.surveyservice.exceptions.QuestionNotFoundException;
import com.pp.surveyservice.exceptions.SurveyNotFoundException;
import com.pp.surveyservice.model.*;
import com.pp.surveyservice.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.routing.key}")
    private String routingKey;

    public Survey createSurvey(CreateSurveyRequest request) {

        List<Question> questions = request.questions();
        questions.forEach(q -> q.setId(UUID.randomUUID().toString()));

        Survey survey = new Survey();
        String surveyId = UUID.randomUUID().toString();
        survey.setId(surveyId);
        survey.setTitle(request.title());
        survey.setDescription(request.description());
        survey.setQuestions(questions);
        survey.setCreatorUsername(request.creatorUsername());

        Survey savedSurvey = surveyRepository.save(survey);

        SurveyMessage surveyMessage = new SurveyMessage(savedSurvey.getCreatorUsername(), savedSurvey.getTitle());
        rabbitTemplate.convertAndSend(exchangeName, routingKey, surveyMessage);

        return savedSurvey;
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

